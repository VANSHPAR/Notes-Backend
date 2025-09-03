package org.example.notesapp.repository;

import org.example.notesapp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface noteRepository extends JpaRepository<Note,Long> {
}
