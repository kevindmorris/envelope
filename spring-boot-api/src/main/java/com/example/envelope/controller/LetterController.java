package com.example.envelope.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.envelope.model.Letter;
import com.example.envelope.service.LetterService;

@RestController
@RequestMapping("/api/letters")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Letter createLetter(@RequestBody Letter letter) {
        return letterService.createLetter(letter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Letter> getLetter(@PathVariable Long id) {
        Optional<Letter> letterOpt = letterService.getLetter(id);
        return letterOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Letter> getAllLetters() {
        return letterService.getAllLetters();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Letter> patchContent(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        Optional<Letter> letterOpt = letterService.getLetter(id);
        if (letterOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Letter letter = letterOpt.get();

        if (updates.containsKey("content")) {
            letter.setContent(updates.get("content"));
        } else {
            return ResponseEntity.badRequest().build(); // no content to update
        }

        Letter updated = letterService.createLetter(letter);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLetter(@PathVariable Long id) {
        letterService.deleteLetter(id);
    }
}
