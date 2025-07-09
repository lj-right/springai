package com.suifeng.ai.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeepSeekController {

    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "message", defaultValue = "hello") String messsage) {
        String call = chatModel.call(messsage);
        System.out.println(call);
        return call;
    }
}
