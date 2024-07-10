package com.example.demo;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;

public class NoteAiServiceImpl {
    private final AzureOpenAiChatModel chatModel;

    public NoteAiServiceImpl(final AzureOpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    chatModel.call("Povej mi nekaj o Margariti");
}
