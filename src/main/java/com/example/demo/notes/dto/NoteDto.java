package com.example.demo.notes.dto;

import com.example.demo.notes.Note;

import java.time.Instant;

public record NoteDto(Long id, String title, String text, Instant created, Instant modified) {
  public static NoteDto fromNote(final Note note) {
    return new NoteDto(
      note.getId(),
      note.getTitle(),
      note.getText(),
      note.getCreated(),
      note.getModified()
    );
  }
}
