package com.example.ecommerce.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import okhttp3.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class DeepSeekChatAgent {
    private static final String API_URL = "http://api.deepseek.com/v1/chat/completions";
    private final OkHttpClient client;


    private JSONArray conversationHistory = new JSONArray();

    public DeepSeekChatAgent() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public String chat(String userInput, String apiKey) throws IOException {
        // 添加用户消息到历史
        JSONObject userMsg = new JSONObject();
        userMsg.put("role", "user");
        userMsg.put("content", userInput);
        conversationHistory.add(userMsg);

        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "deepseek-chat");
        requestBody.put("messages", conversationHistory);
        requestBody.put("stream", true); // 启用流式响应

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(RequestBody.create(
                        MediaType.parse("application/json"),
                        requestBody.toJSONString()))
                .build();

        // 处理流式响应
        StringBuilder responseBuilder = new StringBuilder();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ResponseBody body =  response.body();
            if (body != null) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(body.byteStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("data: ")) {
                            String data = line.substring(6);
                            if (!data.equals("[DONE]")) {
                                JSONObject chunk = JSON.parseObject(data);
                                String content = chunk.getJSONArray("choices")
                                        .getJSONObject(0)
                                        .getJSONObject("delta")
                                        .getString("content");
                                if (content != null) {
                                    responseBuilder.append(content);
                                    System.out.print(content); // 实时输出
                                }
                            }
                        }
                    }
                }
            }
        }

        // 添加AI回复到历史
        JSONObject aiMsg = new JSONObject();
        aiMsg.put("role", "assistant");
        aiMsg.put("content", responseBuilder.toString());
        conversationHistory.add(aiMsg);

        return responseBuilder.toString();
    }

    public void clearHistory() {
        conversationHistory.clear();
    }
}

