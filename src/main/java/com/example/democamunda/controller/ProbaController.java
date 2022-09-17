package com.example.democamunda.controller;


import com.example.democamunda.dto.ProcessInstanceInfo;
import com.example.democamunda.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("v1/proba")
public class ProbaController {
    private final CardService cardService;


    public ProbaController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ProcessInstanceInfo  startCardProcess(){
        return cardService.startProbaProcess();

    }


}
