package com.example.bigData.mapper;


import com.example.bigData.entity.SensorData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SensorDataMapper {
    public void insert(SensorData data);
}
