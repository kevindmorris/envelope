package com.example.envelope.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.envelope.model.Letter;
import com.example.envelope.repository.LetterRepository;

@Service
public class LetterService {

    @Autowired
    private LetterRepository letterRepository;

    public Letter createLetter(Letter letter) {
        return letterRepository.save(letter);
    }

    public Optional<Letter> getLetter(Long id) {
        return letterRepository.findById(id);
    }

    public List<Letter> getAllLetters() {
        return letterRepository.findAll();
    }

    public Optional<Letter> updateLetter(Long id, String newContent) {
        return letterRepository.findById(id).map(letter -> {
            letter.setContent(newContent);
            return letterRepository.save(letter);
        });
    }

    public void deleteLetter(Long id) {
        letterRepository.deleteById(id);
    }
}
