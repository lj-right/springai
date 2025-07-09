package com.suifeng.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private  ChatClient chatClient;

    @GetMapping("chat")
    public String chat(@RequestParam(value = "msg",defaultValue = "你是谁？") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();

    }
}
