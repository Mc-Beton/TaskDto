package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Mock
    private DbService mockService;

    @Test
    void getAllTasks() {
        //Given
        List<Task> mockList = new ArrayList<>();
        mockList.add(new Task("title1", "content1"));
        mockList.add(new Task("title2", "content2"));

        when(mockService.getAllTasks()).thenReturn(mockList);

        //When
        List<Task> listToCheck = mockService.getAllTasks();

        //Then
        assertEquals(2, listToCheck.size());
    }

    @Test
    void getTask() throws TaskNotFoundException {
        //Given
        Task mockTask = new Task("title1", "content1");
        Long id = 1L;

        when(mockService.getTask(id)).thenReturn(mockTask);

        //When
        Task foundTask = mockService.getTask(id);

        //Then
        assertThat(foundTask)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", mockTask.getId())
                .hasFieldOrPropertyWithValue("title", mockTask.getTitle())
                .hasFieldOrPropertyWithValue("content", mockTask.getContent());
    }

    @Test
    void saveTaskTest() throws TaskNotFoundException {
        Task mockTask = new Task(1L,"title1", "content1");
        Long id = 1L;

        when(mockService.saveTask(mockTask)).thenReturn(mockTask);

        //When
        mockService.saveTask(mockTask);
        List<Task> foundTasks = mockService.getAllTasks();

        //Then
        assertThat(foundTasks)
                .isNotNull();
    }

    @Test
    void deleteTask() {
        Task mockTask = new Task(1L,"title1", "content1");
        Long id = 1L;

        when(mockService.saveTask(mockTask)).thenReturn(mockTask);

        //When
        mockService.saveTask(mockTask);
        List<Task> foundTasks = mockService.getAllTasks();
        foundTasks.remove(id);

        //Then
        assertEquals(0, foundTasks.size());
    }
}