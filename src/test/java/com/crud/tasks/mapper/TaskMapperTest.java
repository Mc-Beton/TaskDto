package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();

    @Test
    void mapToTask() {
        //Given
        TaskDto taskToMap = new TaskDto(1L, "title1", "content");

        //When
        Task mappedTask = taskMapper.mapToTask(taskToMap);

        //Then
        assertThat(mappedTask)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", taskToMap.getId())
                .hasFieldOrPropertyWithValue("title", taskToMap.getTitle())
                .hasFieldOrPropertyWithValue("content", taskToMap.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task taskToMap = new Task("title1", "content");

        //When
        TaskDto mappedTask = taskMapper.mapToTaskDto(taskToMap);

        //Then
        assertThat(mappedTask)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", taskToMap.getId())
                .hasFieldOrPropertyWithValue("title", taskToMap.getTitle())
                .hasFieldOrPropertyWithValue("content", taskToMap.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        //Given
        List<Task> listToMap = new ArrayList<>();
        listToMap.add(new Task("title1", "content1"));
        listToMap.add(new Task("title2", "content2"));

        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(listToMap);

        //Then
        assertThat(mappedList).isNotNull();
    }
}