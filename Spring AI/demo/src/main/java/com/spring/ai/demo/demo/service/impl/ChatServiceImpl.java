package com.spring.ai.demo.demo.service.impl;

import com.spring.ai.demo.demo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService<String> {

    private ChatClient openAIChatClient;

    private ChatClient ollamaAIChatClient;

    public ChatServiceImpl(@Qualifier("openAIChatClient") ChatClient openAIChatClient, @Qualifier("ollamaAIChatClient") ChatClient ollamaAIChatClient){
        this.openAIChatClient=openAIChatClient;
        this.ollamaAIChatClient=ollamaAIChatClient;
    }

    @Override
    public String chat(String query) {
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

 /*       List<AIResponse> aiResponses= ollamaAIChatClient
                .prompt(query)
                .call()
                .entity(new ParameterizedTypeReference<List<AIResponse>>() {
                });
*/

//      to modify the prompt and add extra things to prompt to make it more interactive

        String queryStr="Always write program in JAVA. Now reply for this question : {query}";

        var response= ollamaAIChatClient
                .prompt()
                .user(u->u.text(queryStr).param("query",query))
                .call()
                .content();


        return response;

    }

    @Override
    public String chatTemplate() {

        //Step-1 Prompt Templating

        PromptTemplate promptTemplate = PromptTemplate.builder().template("What is {parent} ? and explain about this : {child}").build();

        // Step-2 Render the Template

        String renderedMsg = promptTemplate.render(Map.of(
                "parent","Spring",
                "child","Spring Boot"
        ));

//        Prompt prompt=new Prompt(renderedMsg);

        // to pass system prompt template

        var systemPromptTemplate= SystemPromptTemplate.builder()
                .template("You are a helpful coding assistant. You are an expert in JAVA programming.")
                .build();

        var systemMessage=systemPromptTemplate.createMessage();

        var userMessage = promptTemplate.createMessage(Map.of(
                "parent","threads",
                "child","virtual threads"
        ));

        System.out.println("method entered ");
        Prompt prompt=new Prompt(systemMessage,userMessage);

        return this.ollamaAIChatClient.prompt(prompt).call().content();

    }
}
