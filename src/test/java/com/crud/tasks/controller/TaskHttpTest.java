package com.crud.tasks.controller;

import com.crud.tasks.config.CoreConfiguration;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
@Import(CoreConfiguration.class)
class TaskHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    void getAllTasksTest() throws Exception {
        //Given
        List<TaskDto> tasksDto = List.of(new TaskDto(1L, "Title", "Content"));
        List<Task> tasks = List.of(new Task(1L, "Title", "Content"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(mapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Content")));
    }

    @Test
    void getTaskWithSpecifiedIdTest() throws Exception {
        //Given
        TaskDto tasksDto = new TaskDto(1L, "Title", "Content");
        Task tasks = new Task(1L, "Title", "Content");

        when(service.getTask(1L)).thenReturn(tasks);
        when(mapper.mapToTaskDto(tasks)).thenReturn(tasksDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Content")));
    }

    @Test
    void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task tasks = new Task(1L, "Title", "Content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(mapper.mapToTask(taskDto)).thenReturn(tasks);
        when(service.saveTask(tasks)).thenReturn(tasks);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task tasks = new Task(1L, "Title", "Content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(mapper.mapToTask(taskDto)).thenReturn(tasks);
        when(service.saveTask(tasks)).thenReturn(tasks);
        when(mapper.mapToTaskDto(tasks)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void deleteTaskTest() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
