package com.crud.tasks.trello.controller;


import com.crud.tasks.config.CoreConfiguration;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.domian.CreatedTrelloCardDto;
import com.crud.tasks.trello.domian.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CoreConfiguration.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTestsuite {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @MockBean
    private DbService service;

    @Test
    void testGetAllBoards() throws Exception {
        //Given
        TrelloBoardDto boardDto1 = new TrelloBoardDto();
        TrelloBoardDto boardDto2 = new TrelloBoardDto();
        List<TrelloBoardDto> boardList = new ArrayList<>();

        boardList.add(boardDto1);
        boardList.add(boardDto2);

        when(trelloFacade.fetchTrelloBoards()).thenReturn(boardList);

        //When & Then
        this.mvc.perform(MockMvcRequestBuilders.get("/v1/trello/boards"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));

    }

    @Test
    void saveNewCard() throws Exception {
        //Given
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newCard);

        this.mvc.perform(MockMvcRequestBuilders.post("/v1/trello/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }



}
