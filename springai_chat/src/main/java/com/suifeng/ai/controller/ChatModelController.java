package com.suifeng.ai.controller;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ChatModelController {
    @Autowired
    private ChatModel chatModel;



    /**
     * 提示词操作
     * @param name
     * @param voice
     * @return
     */
    @GetMapping("/prompt")
    private String prompt(@RequestParam("name") String name, @RequestParam("voice") String voice) {
        //设置用户输入信息
        String userText = "给我推荐Java编程中使用的冷知识";
        UserMessage userMessage = new UserMessage(userText);

        //设置系统提示信息
        String systemText = "你是一个编程知识小助手，帮助程序员学习更多的编程技术。" +
                "你的名字是{name},你应该用你的名字和对{voice}的熟悉程度回复用户的请求。";

        //Promt Template 设置相关信息
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);

        //替换占位符
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "voice", voice));

        //使用Prompt封装
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        //调用chatModel方法
        ChatResponse response = chatModel.call(prompt);

        List<Generation> results = response.getResults();

        return results.stream().map(a ->
                a.getOutput().getContent()).collect(Collectors.joining(""));

    }





    @GetMapping("/chatModel01")
    public String chatMOdel01(@RequestParam(value = "msg") String msg) {
        String result = chatModel.call(msg);
        return result;
    }
    @GetMapping("/chatModel02")
    public String chatMOdel02(@RequestParam(value = "msg") String msg) {
        ChatResponse chatResponse = chatModel.call(new Prompt(
                msg,
                OpenAiChatOptions.builder()
                        .model("deepseek-chat")
                        .temperature(0.8)
                        .build()));

        return chatResponse.getResult().getOutput().getContent();
    }
}
