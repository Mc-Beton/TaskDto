package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.domian.TrelloBoardDto;
import com.crud.tasks.trello.domian.TrelloCardDto;
import com.crud.tasks.trello.domian.TrelloListDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperSuiteTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void shouldMapToBoard() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        TrelloBoardDto boardToMap = new TrelloBoardDto("1", "name1", trelloListDto);

        //When
        TrelloBoard mappedBoard = trelloMapper.mapToBoard(boardToMap);

        //Then
        assertThat(mappedBoard)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", boardToMap.getId())
                .hasFieldOrPropertyWithValue("name", boardToMap.getName());
    }

    @Test
    void shouldMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();

        List<TrelloBoardDto> listToMap = new ArrayList<>();
        listToMap.add(new TrelloBoardDto("1", "boardName1", trelloListDto));
        listToMap.add(new TrelloBoardDto("2", "boardName2", trelloListDto));

        //When
        List<TrelloBoard> mappedBoard;
        mappedBoard = trelloMapper.mapToBoards(listToMap);

        //Then
        assertEquals(2, mappedBoard.size());
        assertThat(mappedBoard.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", listToMap.get(0).getId())
                .hasFieldOrPropertyWithValue("name", listToMap.get(0).getName());
    }

    @Test
    void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();

        List<TrelloBoard> listToMap = new ArrayList<>();
        listToMap.add(new TrelloBoard("1", "boardName1", trelloList));
        listToMap.add(new TrelloBoard("2", "boardName2", trelloList));

        //When
        List<TrelloBoardDto> mappedBoard = trelloMapper.mapToBoardsDto(listToMap);

        //Then
        assertEquals(2, mappedBoard.size());
        assertThat(mappedBoard.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", listToMap.get(0).getId())
                .hasFieldOrPropertyWithValue("name", listToMap.get(0).getName());

    }

    @Test
    void shouldMapToList() {
        //Given
        List<TrelloListDto> listToMap = new ArrayList<>();
        listToMap.add(new TrelloListDto("1", "name1", false));
        listToMap.add(new TrelloListDto("2", "name2", false));

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(listToMap);

        //Then
        assertEquals(2, mappedList.size());
        assertThat(mappedList.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", listToMap.get(0).getId())
                .hasFieldOrPropertyWithValue("name", listToMap.get(0).getName());

    }

    @Test
    void shouldMapToListDto() {
        //Given
        List<TrelloList> listToMap = new ArrayList<>();
        listToMap.add(new TrelloList("1", "name1", false));
        listToMap.add(new TrelloList("2", "name2", false));

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(listToMap);

        //Then
        assertEquals(2, mappedList.size());
        assertThat(mappedList.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", listToMap.get(0).getId())
                .hasFieldOrPropertyWithValue("name", listToMap.get(0).getName());

    }

    @Test
    void shouldMapToTrelloCard() {
        //Given
        TrelloCardDto cardToMap = new TrelloCardDto("name1", "desc1", "pos1", "1");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(cardToMap);

        //Then
        assertThat(mappedCard)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", cardToMap.getName())
                .hasFieldOrPropertyWithValue("description", cardToMap.getDescription())
                .hasFieldOrPropertyWithValue("pos", cardToMap.getPos())
                .hasFieldOrPropertyWithValue("listId", cardToMap.getListId());
    }

    @Test
    void shouldMapToTrelloCardDto() {
        //Given
        TrelloCard cardToMap = new TrelloCard("name1", "desc1", "pos1", "1");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(cardToMap);

        //Then
        assertThat(mappedCard)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", cardToMap.getName())
                .hasFieldOrPropertyWithValue("description", cardToMap.getDescription())
                .hasFieldOrPropertyWithValue("pos", cardToMap.getPos())
                .hasFieldOrPropertyWithValue("listId", cardToMap.getListId());
    }
}
