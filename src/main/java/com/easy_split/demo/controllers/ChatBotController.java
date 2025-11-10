package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.chat.ChatBotRequestDTO;
import com.easy_split.demo.services.ChatBotService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping
    public String chat(@Valid @RequestBody ChatBotRequestDTO prompt) {
        ChatLanguageModel model = this.chatBotService.chat("llama3.2");
        return model.generate(prompt.getMessage());
    }
}
