package com.example.ecommerce.ai;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/chat")
public class MainController {
    private final DeepSeekChatAgent agent;
    private final String apiKey = "sk-5666fa1b2f3c4d059099377b9299c803";

    public MainController() {
        this.agent = new DeepSeekChatAgent();
    }

    @PostMapping("/stream")
    public SseEmitter streamChat(@RequestBody ChatRequest request) {
        SseEmitter emitter = new SseEmitter(60_000L);
        new Thread(() -> {
            try {
                String response = agent.chat(request.getMessage(), apiKey);
                emitter.send(SseEmitter.event()
                        .name("message")
                        .data(response));
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }

    @PostMapping("/reset")
    public String resetConversation() {
        agent.clearHistory();
        return "Conversation history cleared";
    }
}

