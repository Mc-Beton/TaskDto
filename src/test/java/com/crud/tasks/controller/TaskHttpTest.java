package com.crud.tasks.controller;

import com.crud.tasks.config.CoreConfiguration;
import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CoreConfiguration.class)
@WebMvcTest(TaskController.class)
public class TaskHttpTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DbService service;

    @Test
    public void testGetAllTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("title1", "content1"));
        taskList.add(new Task("title2", "content2"));

        when(service.getAllTasks()).thenReturn(taskList);

        //When & Then
        this.mvc
                .perform(MockMvcRequestBuilders.get("/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("title1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("content1"));
    }

    @Test
    void testGetTaskById() throws Exception {
        //Given
        Task task = new Task(1L,"title1", "content1");
        Long taskId = task.getId();

        when(service.getTask(taskId)).thenReturn(task);

        //When & Then
        this.mvc
                .perform(MockMvcRequestBuilders.get("/v1/tasks/{id}", taskId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("content1"));
    }

    @Test
    void testSaveNewTask() throws Exception {
        //Given
        Task task = new Task("title1", "content1");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(task);

        this.mvc.perform(MockMvcRequestBuilders.post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteTask() throws Exception {
        //Given & When & Then
        this.mvc.perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    void catchesExceptionWhenEntityNotFoundWithSpecificResponse() throws Exception, TaskNotFoundException {
        //Given
        Long exceptionParam = 1L;

        when(service.getTask(1L)).thenThrow(new TaskNotFoundException());

        //When & Then
        mvc.perform(MockMvcRequestBuilders.get("/v1/tasks/{id}", exceptionParam))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFoundException));
    }
}
