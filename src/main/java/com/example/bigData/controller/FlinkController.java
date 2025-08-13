package com.example.bigData.controller;

import com.example.bigData.service.FlinkService;
import com.example.bigData.service.KafkaService;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flink")
public class FlinkController {
    @Autowired
    private FlinkService flinkService;
    @GetMapping("/consumer")
    public String consumerTopic() {
        try {

            flinkService.consumer();
            return "订阅成功";
        } catch (Exception e) {
            return "订阅失败：" + e.getMessage();
        }
    }
}
