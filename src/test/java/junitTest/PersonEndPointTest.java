package junitTest;

import com.example.democamunda.controller.PersonController;
import com.example.democamunda.dto.PersonDto;
import com.example.democamunda.service.PersonService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)

public class PersonEndPointTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonService personService;

    @Test
    public void testAddPerson() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PersonDto user = new PersonDto();
        user.setCreated(LocalDateTime.parse("2022-09-17 11:26:42", formatter));
        user.setUpdated(LocalDateTime.parse("2022-09-17 11:26:42", formatter));

        MvcResult result = mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andReturn();
        assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testAddUserInFail() throws Exception {
        PersonDto user = new PersonDto();
        MvcResult result = mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andReturn();
        assertEquals(result.getResponse().getStatus(), 400);

    }

}
