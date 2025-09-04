package org.example.notesapp.controller;

import jakarta.transaction.Transactional;
import org.example.notesapp.models.Note;
import org.example.notesapp.repository.noteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/app/note")
@CrossOrigin("*")
public class noteController {
    private final noteRepository noteRepository;
    public noteController(noteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Note> createNote(@RequestParam String title, @RequestParam String content){
        Note note=Note.builder().title(title).content(content).shareId(UUID.randomUUID().toString()).build();
        noteRepository.save(note);
        return ResponseEntity.ok(note);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Note>> getAllNote(){
        Optional<List<Note>> notes= Optional.of(noteRepository.findAll());
        return  ResponseEntity.ok(notes.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> readNote(@PathVariable Long id){
        Optional<Note> note=noteRepository.findById(id);
        if(note.isPresent()){
            return ResponseEntity.ok(note.get());
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/share/{shareId}")
    public ResponseEntity<Note> getNoteByshareId(@PathVariable String shareId){
        Optional<Note> note=noteRepository.findNoteByShareId(shareId);
        if(note.isPresent()){
            return ResponseEntity.ok(note.get());

        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateNote(@PathVariable Long id,@RequestParam String title, @RequestParam String content){
        Optional<Note> note=noteRepository.findById(id);
        if(note.isPresent()){
            if(title!=null){
                note.get().setTitle(title);
            }
            if(content!=null){
                note.get().setContent(content);
            }
            return ResponseEntity.ok(note.get());
        }
     return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id){
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
