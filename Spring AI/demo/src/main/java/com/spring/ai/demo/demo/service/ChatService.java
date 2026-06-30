package com.spring.ai.demo.demo.service;

import reactor.core.publisher.Flux;

public interface ChatService <T> {
    T chat(String query);
    T chatTemplate();

    T chatTemplateByFiles();
    T chatAdvisors(String query);

    Flux<String> streamChat(String query);

    T chatMemory(String query);

    T chatMemoryByConvID(String query, String userID);
}
