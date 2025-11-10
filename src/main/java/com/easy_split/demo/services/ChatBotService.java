package com.easy_split.demo.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    public ChatLanguageModel chat(String model) {
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName(model)
                .timeout(java.time.Duration.ofSeconds(240))
                .build();
    }
}
