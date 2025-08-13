package com.example.bigData.service;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class FlinkKafkaService {

    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    public void consumer() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", bootstrapServers);
        props.setProperty("group.id", "flink-consumer-group");
        props.setProperty("auto.offset.reset", "latest");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                topic,
                new SimpleStringSchema(),
                props
        );

        env.addSource(consumer).print();
        env.execute("Kafka Consumer Job");
    }
}
