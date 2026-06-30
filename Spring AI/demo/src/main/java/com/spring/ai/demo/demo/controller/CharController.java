package com.spring.ai.demo.demo.controller;

import com.spring.ai.demo.demo.entity.AIResponse;
import com.spring.ai.demo.demo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

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
    public ResponseEntity<String> chatTemplate(){
        //       List<AIResponse> results= (List<AIResponse>) chatService.chat(query);
        String response = (String) chatService.chatTemplate();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatTemplateByFiles")
    public ResponseEntity<String> chatTemplateByFiles(){

        String response = (String) chatService.chatTemplateByFiles();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatAdvisors")
    public ResponseEntity<String> chatAdvisors(@RequestParam(value = "query",required = true)String query){

        String response = (String) chatService.chatAdvisors(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/streamChat")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "query",required = true)String query){

        return ResponseEntity.ok(chatService.streamChat(query));
    }

    @GetMapping("/chatMemory")
    public ResponseEntity<String> chatMemory(@RequestParam(value = "query",required = true)String query){

        String response = (String) chatService.chatMemory(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatMemoryByConversationId")
    public ResponseEntity<String> chatMemory(@RequestParam(value = "query",required = true)String query,@RequestHeader("userID")String userID){

        String response = (String) chatService.chatMemoryByConvID(query,userID);
        return ResponseEntity.ok(response);
    }
}
