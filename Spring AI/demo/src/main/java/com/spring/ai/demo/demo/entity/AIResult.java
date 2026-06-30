package com.spring.ai.demo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIResult {

    private String markdown;

    private List<AIResponse> responses;
}