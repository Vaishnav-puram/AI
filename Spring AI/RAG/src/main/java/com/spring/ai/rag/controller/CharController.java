package com.spring.ai.rag.controller;

import com.spring.ai.rag.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/spring-ai")
public class CharController {

    private ChatService chatService;

    public CharController(ChatService chatService){
        this.chatService=chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "query",required = true)String query){
 //       List<AIResponse> results= (List<AIResponse>) chatService.chat(query);
        String response = (String) chatService.chat(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatTemplate")
    public ResponseEntity<String> chatTemplate(@RequestParam(value = "query",required = true)String query){
        //       List<AIResponse> results= (List<AIResponse>) chatService.chat(query);
        String response = (String) chatService.chatTemplate(query);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/streamChat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "query",required = true)String query){

        return ResponseEntity.ok(chatService.streamChat(query));
    }
}
