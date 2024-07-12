package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ChatgptService {
    private final AzureOpenAiChatModel chatModel;

    public ChatgptService(final AzureOpenAiChatModel chatModel, final MeterRegistry meterRegistry) {
        this.chatModel = chatModel;
        this.chatGptResponseTimer = meterRegistry.timer("chat_gpt_response_timer");
    }

    private final Logger logger = LoggerFactory.getLogger(ChatgptService.class.getName());
    private final Timer chatGptResponseTimer;

    @GetMapping("/chatgpt")
    public String getChatgpt(@RequestParam String prompt) {
        logger.info("Fetching response from openai about: " + prompt);
        final long startTime = System.currentTimeMillis();
        String chatGptResponse = chatModel.call(prompt);
        chatGptResponseTimer.record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        logger.info("Response from chatgpt: " + chatGptResponse);
        return chatGptResponse;
    }
}
