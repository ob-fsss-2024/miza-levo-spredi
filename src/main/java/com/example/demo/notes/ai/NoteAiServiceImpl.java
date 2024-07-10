package com.example.demo.notes.ai;

import com.example.demo.notes.Note;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NoteAiServiceImpl implements NoteAiService {
    private final AzureOpenAiChatModel chatModel;

    public NoteAiServiceImpl(final AzureOpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Note generateRandomNote() {
        return new Note(
                "Ai generated note",
                chatModel.call("Create a random to do item that would appear on a random to do list. Be creative and try not to repeat yourself. Return only the description, nothing else and do not include dot at the end.")
        );
    }
}
