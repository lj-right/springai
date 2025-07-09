package com.suifeng.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatAiController {

    @Autowired
    private  ChatClient chatClient;

    //角色预设，非流式响应
    @GetMapping("/chatAi")
    public String chatAi (@RequestParam(value = "msg",defaultValue = "你是谁？") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();

    }
    //角色预设，流式响应
    @GetMapping(value = "/chatAiStream",produces = "text/html;charset=UTF-8")
    public Flux<String> chatAiStream (@RequestParam(value = "msg",defaultValue = "你是谁？") String message){
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();

    }
}
