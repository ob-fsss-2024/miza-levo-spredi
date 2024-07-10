package com.example.demo.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String text;
  @CreationTimestamp
  private Instant created;
  @UpdateTimestamp
  private Instant modified;

  public Note() {

  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Instant getCreated() {
    return created;
  }

  public Instant getModified() {
    return modified;
  }

  public Note(final String title, final String text) {
    this.title = title;
    this.text = text;
  }
}
