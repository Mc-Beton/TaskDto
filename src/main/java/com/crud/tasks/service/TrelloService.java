package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.domian.CreatedTrelloCardDto;
import com.crud.tasks.trello.domian.TrelloBoardDto;
import com.crud.tasks.trello.domian.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {
    private final static String SUBJECT = "Subject: New card created";
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards () {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(
                new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    "New card: " + trelloCardDto.getName() + " has been created on your Trello account",
                    null
                )));
        return newCard;
    }
}