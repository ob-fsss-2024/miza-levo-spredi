package com.example.demo.notes;

import com.example.demo.notes.ai.NoteAiService;
import com.example.demo.notes.dto.CreateNoteDto;
import com.example.demo.notes.dto.UpdateNoteDto;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class NoteService {
    private final Logger logger = LoggerFactory.getLogger(NoteService.class.getName());
    private final NoteRepository noteRepository;
    private final NoteAiService noteAiService;
    private final Counter noteCreationCounter;
    private final Timer noteCreationTimer;

    @Autowired
    public NoteService(final NoteRepository noteRepository, final NoteAiService noteAiService, final MeterRegistry metricsRegistry) {
        this.noteRepository = noteRepository;
        this.noteAiService = noteAiService;
        this.noteCreationCounter = metricsRegistry.counter("note_creation_counter");
        this.noteCreationTimer = metricsRegistry.timer("note_creation_timer");
    }

    public List<Note> getNotes(final String keyword) {
        logger.info("Fetching notes with keyword: {}", keyword);
        if (keyword == null || keyword.isBlank()) {
            return this.noteRepository.findAll();
        } else {
            return this.noteRepository.findNoteByTextLike(keyword);
        }
    }

    public Note getNote(final Long id) {
        logger.info("Fetching note with id {}", id);
        return this.noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No note"));
    }

    public Note createNote(final CreateNoteDto createNoteDto) {
        final long startTime = System.currentTimeMillis();
        if (createNoteDto.text() == null || createNoteDto.title().isBlank() || createNoteDto.text().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty note");
        }

        final Note note = new Note(createNoteDto.title(), createNoteDto.text());

        final Note createdNote = noteRepository.save(note);
        noteCreationCounter.increment();
        noteCreationTimer.record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);

        logger.info("Created note with id {}", createdNote.getId());
        return createdNote;
    }

    public Note updateNote(final UpdateNoteDto updateNoteDto, final Long id) {
        if (updateNoteDto.text() == null || (updateNoteDto.text().isBlank() && updateNoteDto.title().isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty note or nothing to update");
        }

        final Optional<Note> existingNoteOptional = noteRepository.findById(id);
        if (existingNoteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id");
        }
        final Note existingNote = existingNoteOptional.get();


        if (!updateNoteDto.title().isBlank()) {
            existingNote.setTitle(updateNoteDto.title());
        }

        if (!updateNoteDto.text().isBlank()) {
            existingNote.setText(updateNoteDto.text());
        }

        logger.info("Updating note {}, new note={}", existingNote.getId(), existingNote);
        return noteRepository.save(existingNote);
    }

    public void deleteNote(final Long id) {
        noteRepository.deleteById(id);
    }

    public Note generateRandomNote() {
        final Note noteToCreate = noteAiService.generateRandomNote();
        return noteRepository.save(noteToCreate);
    }
}
