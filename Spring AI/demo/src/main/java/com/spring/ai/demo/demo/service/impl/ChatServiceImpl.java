package com.spring.ai.demo.demo.service.impl;

import com.spring.ai.demo.demo.entity.AIResponse;
import com.spring.ai.demo.demo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient openAIChatClient;

    private ChatClient ollamaAIChatClient;

    public ChatServiceImpl(@Qualifier("openAIChatClient") ChatClient openAIChatClient, @Qualifier("ollamaAIChatClient") ChatClient ollamaAIChatClient){
        this.openAIChatClient=openAIChatClient;
        this.ollamaAIChatClient=ollamaAIChatClient;
    }

    @Override
    public List<AIResponse> chat(String query) {
//        return ollamaAIChatClient
//                .prompt()
//                .user(query)
//                .system("As Cricket Expert")
//                .call()
//                .content();

//        return ollamaAIChatClient
//                .prompt(query)
//                .call()
//                .content();

//        return ollamaAIChatClient
//                .prompt(query)
//                .call()
//                .chatResponse()
//                .getResult()
//                .getOutput()
//                .getText();

        // to get metadata
//        return ollamaAIChatClient
//                .prompt(query)
//                .call()
//                .chatResponse()
//                .getMetadata()
//                .toString();
        //{ id: , usage: DefaultUsage{promptTokens=29, completionTokens=692, totalTokens=721}, rateLimit: org.springframework.ai.chat.metadata.EmptyRateLimit@bff3bd2 }

        // convert response into entity
//        AIResponse aiResponse= ollamaAIChatClient
//                .prompt(query)
//                .call()
//                .entity(AIResponse.class);
//
//        return aiResponse;

        List<AIResponse> aiResponses= ollamaAIChatClient
                .prompt(query)
                .call()
                .entity(new ParameterizedTypeReference<List<AIResponse>>() {
                });

        return aiResponses;

    }
}
