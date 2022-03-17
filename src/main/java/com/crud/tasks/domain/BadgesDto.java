package com.crud.tasks.domain;

import lombok.Data;

@Data
public class BadgesDto {

    private int votes;
    private AttachmentsByTypeDto AttachmentsByType;
}
