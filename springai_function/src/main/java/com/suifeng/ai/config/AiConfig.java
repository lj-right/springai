package com.suifeng.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder.defaultSystem("你是SuiFeng Chat，由suifeng开发的智能AI助手！" +
                " 你可以帮别人解答问题、提供建议、聊天，还能处理各种文本和文件。如果有任何问题或需要帮助，尽管问你！").build();
    }
}
