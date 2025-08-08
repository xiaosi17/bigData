package com.example.ecommerce.ai;

// 对话消息类
class Message {
    private String role;  // user/assistant
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters
    public String getRole() { return role; }
    public String getContent() { return content; }
}
