package com.example.demo.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  @Query("select n from Note n where n.text like %?1%")
  List<Note> findNoteByTextLike(String keyword);
}
