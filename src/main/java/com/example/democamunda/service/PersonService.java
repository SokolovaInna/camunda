package com.example.democamunda.service;

import com.example.democamunda.dto.PersonDto;
import com.example.democamunda.model.Person;
import com.example.democamunda.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository repository;
    private final ModelMapper modelMapper;

    public PersonService(PersonRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public PersonDto addPerson(PersonDto dto) {
        Person person = modelMapper.map(dto, Person.class);
        Person save = repository.save(person);
        PersonDto dto1=new PersonDto();
        dto1.setId(save.getId());
        dto1.setName(save.getName());
        dto1.setCreated(save.getCreated());
        dto1.setUpdated(save.getUpdated());

        return  dto1;

    }
}
