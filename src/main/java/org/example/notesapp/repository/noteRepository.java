package org.example.notesapp.repository;

import org.example.notesapp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface noteRepository extends JpaRepository<Note,Long> {
    Optional<Note> findNoteByShareId(String shareId);
}
