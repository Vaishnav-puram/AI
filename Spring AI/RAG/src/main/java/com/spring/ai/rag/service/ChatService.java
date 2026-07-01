package com.spring.ai.rag.service;

import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService <T> {
    T chat(String query);
    T chatTemplate(String query);

    Flux<String> streamChat(String query);

    void saveDataDump(List<String> data);

}
