package com.example.demo.notes.ai;

import com.example.demo.notes.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod")
public class NoteAiServiceMock implements NoteAiService {
    @Override
    public Note generateRandomNote() {
        return new Note(
                "Mocked note",
                "No access to AI model locally. Mocked note generated."
        );
    }
}
