package com.example.demo;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteAiServiceImpl {
    private final AzureOpenAiChatModel chatModel;

    public NoteAiServiceImpl(final AzureOpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chatgpt")
    public String getChatgpt(@RequestParam String prompt) {
        return chatModel.call(prompt);
    }
}
