package com.spring.ai.rag.service.impl;

import com.spring.ai.rag.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService<String> {

    private ChatClient ollamaAIChatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    @Autowired
    VectorStore vectorStore;

    public ChatServiceImpl(@Qualifier("ollamaAIChatClient") ChatClient ollamaAIChatClient){
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
    public String chatTemplate(String query) {

        log.info("chatTemplate method called");
        // load data from vector database
        SearchRequest searchRequest = SearchRequest.builder()
                .topK(5)
                .similarityThreshold(0.6) // 0.0 to 1.0 It is a minimum similarity score that a document must achieve to be considered relevant
                .query(query)
                .build();

        // similar result fetched as of user query
        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        List<String> similarDocuments = documents.stream().map(content->content.getText()).toList();
        String context=String.join(",",similarDocuments);

        log.info("Context Data : {}",context);



        return ollamaAIChatClient
                .prompt()
                .system(system->
                        system.text(systemMessage).param("document",context)) // pass that in context (input query + context)
                .user(user->
                        user.text(userMessage).param("query",query))
                .call()
                .content();

    }

    @Override
    public Flux<String> streamChat(String query) {


        return ollamaAIChatClient
                .prompt()
                .system(system->
                        system.text(this.systemMessage))
                .user(user->
                        user.text(this.userMessage).param("concept",query))
                .stream()
                .content();
    }

    @Override
    public void saveDataDump(List<String> data) {

       List<Document> documents = data.stream().map(Document::new).toList();

       vectorStore.add(documents);

    }

}
