package com.example.democamunda.controller;

import com.example.democamunda.dto.PersonDto;
import com.example.democamunda.model.Person;
import com.example.democamunda.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("person")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto startCardProcess(@RequestBody @Valid PersonDto dto){
        return personService.addPerson(dto);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto startCardProcess(){
        PersonDto dto=new PersonDto();
        dto.setName("Inna");
        dto.setCreated(LocalDateTime.now());
        dto.setUpdated(LocalDateTime.now());
        return dto;
    }
}
