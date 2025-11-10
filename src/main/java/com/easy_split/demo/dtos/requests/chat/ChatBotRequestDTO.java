package com.easy_split.demo.dtos.requests.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatBotRequestDTO {

    @NotNull(message = "Message is required")
    @NotEmpty(message = "Message can't be null")
    private String message;
}
