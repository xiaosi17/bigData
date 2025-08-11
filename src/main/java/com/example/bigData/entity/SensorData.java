package com.example.bigData.entity;

import com.example.bigData.dto.SensorDataReq;
import com.example.bigData.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    private String id;
    private String deviceId;
    private Double temperature;
    private Double humidity;
    private LocalDateTime createTime;

    public static SensorData buildBy(SensorDataReq sensorDataReq) {
        SensorData sensorData = new SensorData();
        sensorData.setId(IdGenerator.generate() + System.currentTimeMillis());
        sensorData.setDeviceId(sensorDataReq.getDeviceId());
        sensorData.setHumidity(sensorDataReq.getHumidity());
        sensorData.setTemperature(sensorDataReq.getTemperature());
        sensorData.setCreateTime(LocalDateTime.now());
        return sensorData;
    }
}
