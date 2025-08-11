package com.example.bigData.controller;

import com.alibaba.fastjson.JSON;
import com.example.bigData.dto.SensorDataReq;
import com.example.bigData.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;
    @PostMapping("/publish")
    public String publishMessage(@RequestBody SensorDataReq sensorDataReq) {
        // 对象转JSON字符串
        String jsonStr = JSON.toJSONString(sensorDataReq);
        try {
            kafkaService.publish(jsonStr);
            return "消息发布成功";
        } catch (Exception e) {
            return "消息发布失败：" + e.getMessage();
        }
    }

    @GetMapping("/consumer")
    public String consumerTopic() {
        try {
            kafkaService.consumer();
            return "订阅成功";
        } catch (Exception e) {
            return "订阅失败：" + e.getMessage();
        }
    }
}
