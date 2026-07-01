package com.spring.ai.rag.config;

import com.spring.ai.rag.advisors.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AIConfig {

    @Bean
    public ChatMemory chatMemory(){
        InMemoryChatMemoryRepository inMemoryChatMemoryRepository = new InMemoryChatMemoryRepository();
        return MessageWindowChatMemory.builder().maxMessages(10).chatMemoryRepository(inMemoryChatMemoryRepository).build();
    }

    @Bean(name = "ollamaAIChatClient")
    public ChatClient ollamaAiChatModel(OllamaChatModel ollamaChatModel,ChatMemory chatMemory){
       // return ChatClient.builder(ollamaChatModel).build();

        MessageChatMemoryAdvisor messageChatMemoryAdvisor=MessageChatMemoryAdvisor.builder(chatMemory).build();
        //to set default options
        return ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(messageChatMemoryAdvisor,new TokenPrintAdvisor(),new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of("punch")))
                .defaultSystem("You are a helpful coding AI assistant")
                .defaultOptions(OllamaChatOptions.builder()
                        .model("phi3:mini")
                        .temperature(0.7)
                        .numPredict(1026)
                        .build())
                .build();
    }
}
