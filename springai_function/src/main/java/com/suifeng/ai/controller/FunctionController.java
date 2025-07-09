package com.suifeng.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

    @Autowired
    private ChatModel chatModel;

    @GetMapping(value = "function",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public String function01(@RequestParam("userMessage") String userMessage) {
       return ChatClient.builder(chatModel)
               .build()
               .prompt()
               .system("您是算数计算器的代理，支持加法运算，乘法运算等操作，其他问题暂不支持请告知等待更新," +
                       "在进行加法运算，乘法运算等操作前，您必须从用户处获取如下信息：两个数字，运算类型（可以根据用户给出的符号进行区分呢）" +
                       "请调用自定义函数执行加法运算，乘法运算。请讲中文。")
               .user(userMessage)
               .functions("addOperation","mulOperation")
               .call()
               .content();
    }
}
