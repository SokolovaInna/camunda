package com.example.democamunda;

import com.example.democamunda.dto.PersonDto;
import com.example.democamunda.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CamundaPetApplicationTests {
    @Autowired
    private RestTemplate template = new RestTemplate();
    @Autowired
    protected PersonRepository repository ;
    @BeforeEach()
    public void clearDatabase() {
       repository.deleteAll();
           }

    @Test
    void contextLoads() {
    }


    @Test
    public void testPost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PersonDto user = new PersonDto();
        user.setName("Irina");
        user.setCreated(LocalDateTime.parse("2022-09-17 11:26:42", formatter));
        user.setUpdated(LocalDateTime.parse("2022-09-17 11:26:42", formatter));
        PersonDto  actualUser = template.postForObject("http://localhost:8080/person", user, PersonDto.class);
        assertEquals(user.getName(), actualUser.getName());
    }
    @Test
    public void testPostFatal() {
        PersonDto user = new PersonDto();
        HttpClientErrorException exc = assertThrows(HttpClientErrorException.class, ()-> {
            PersonDto  actualUser = template.postForObject("http://localhost:8080/person", user, PersonDto.class);
        });
        exc.getResponseBodyAsString();
        assertTrue(exc.getResponseBodyAsString().contains("name:не должно равняться null"));

    }
}
