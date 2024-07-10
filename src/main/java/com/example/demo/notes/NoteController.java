package com.example.demo.notes;

import com.example.demo.notes.dto.CreateNoteDto;
import com.example.demo.notes.dto.NoteDto;
import com.example.demo.notes.dto.UpdateNoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("notes")
public class NoteController {
  private final NoteService noteService;

  @Autowired
  public NoteController(final NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public List<NoteDto> getNotes(@RequestParam(required = false) final String keyword) {
    return noteService
      .getNotes(keyword)
      .stream()
      .map(NoteDto::fromNote)
      .toList();
  }

  @GetMapping("{id}")
  public NoteDto getNote(@PathVariable Long id) {
    return NoteDto.fromNote(noteService.getNote(id));
  }

  @PostMapping
  public NoteDto createNote(@RequestBody final CreateNoteDto createNoteDto) {
    return NoteDto.fromNote(noteService.createNote(createNoteDto));
  }

  @PostMapping("/random")
  public NoteDto generateRandomeNote() {
    return NoteDto.fromNote(noteService.generateRandomNote());
  }

  @PutMapping("/{id}")
  public NoteDto updateNote(@RequestBody final UpdateNoteDto updateNoteDto, @PathVariable Long id) {
    return NoteDto.fromNote(noteService.updateNote(updateNoteDto, id));
  }

  @DeleteMapping("/{id}")
  public void deleteNote(@PathVariable final Long id) {
    noteService.deleteNote(id);
  }
}
