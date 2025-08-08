package com.example.ecommerce.ai;
import lombok.Data;
import java.util.List;
@Data
public class ChatRequest {
    private String message;
    private Float temperature;
    private Integer maxTokens;
    private List<Message> history;


}



