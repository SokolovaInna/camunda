package com.example.workflow.controller;

import com.example.workflow.task.utils.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class ServiceImitationControlller {
    @PostMapping("/start")
    public Message getServiceMessage(@RequestBody String request) {
        Message message = new Message();
        Random r = new Random();

        switch ( r.nextInt(2)) {
            case (0):
                message.setStatus("Ok");
                break;
            case (1):
                message.setStatus("Error");
                break;
            case (2):
                message.setStatus("Timeout");
                break;
        }
        return message;
    }
}
