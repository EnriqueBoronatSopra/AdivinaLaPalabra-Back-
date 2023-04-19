package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.service.WordExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Parameter;

@Controller
public class WordExistController {
    @Autowired
    private WordExistServiceImpl wordServiceImpl;

    @GetMapping("/checkWord")
    public ResponseEntity<Boolean> wordExist(@RequestParam String word) {
        boolean wordExistInDictionary = wordServiceImpl.existWordOrNot(word);
        return new ResponseEntity<>(wordExistInDictionary, HttpStatus.OK);
    }

}
