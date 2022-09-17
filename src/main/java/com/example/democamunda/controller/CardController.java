package com.example.democamunda.controller;


import com.example.democamunda.dto.CardDto;
import com.example.democamunda.dto.ProcessInstanceInfo;
import com.example.democamunda.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/card")
public class CardController {
    private final CardService cardService;


    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ProcessInstanceInfo  startCardProcess(@RequestBody @Valid CardDto dto){
        return cardService.startCardProcess(dto);

    }


}
