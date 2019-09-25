package com.zlx.pachong.controller;

import com.zlx.pachong.dto.Message;
import com.zlx.pachong.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PaChong {

    @Autowired
    MessageService messageService;
    @GetMapping(value = "test")
    public Message getMapper() throws IOException, IllegalAccessException {
        return messageService.saveMessage();


    }
}
