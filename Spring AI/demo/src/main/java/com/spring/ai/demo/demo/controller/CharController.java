package com.spring.ai.demo.demo.controller;

import com.spring.ai.demo.demo.entity.AIResponse;
import com.spring.ai.demo.demo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spring-ai")
public class CharController {

    private ChatService chatService;

    public CharController(ChatService chatService){
        this.chatService=chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<List<AIResponse>> chat(@RequestParam(value = "query",required = true)String query){
        List<AIResponse> results= (List<AIResponse>) chatService.chat(query);

        return ResponseEntity.ok(results);
    }
}
