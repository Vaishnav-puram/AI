package com.spring.ai.demo.demo.service;

public interface ChatService <T> {
    T chat(String query);
    T chatTemplate();

    T chatTemplateByFiles();
    T chatAdvisors(String query);
}
