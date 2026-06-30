package com.spring.ai.demo.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AIConfig {

    @Bean(name = "openAIChatClient")
    public ChatClient openAiChatModel(OpenAiChatModel openAiChatModel){
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean(name = "ollamaAIChatClient")
    public ChatClient ollamaAiChatModel(OllamaChatModel ollamaChatModel){
       // return ChatClient.builder(ollamaChatModel).build();

        //to set default options
        return ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of("punch")))
                .defaultSystem("You are a helpful coding AI assistant")
                .defaultOptions(OllamaChatOptions.builder()
                        .model("phi3:mini")
                        .temperature(0.7)
                        .build())
                .build();
    }
}
