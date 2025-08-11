package com.example.bigData.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SensorDataReq {
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("humidity")
    private Double humidity;
}
