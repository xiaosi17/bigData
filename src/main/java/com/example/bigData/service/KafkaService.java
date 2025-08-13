package com.example.bigData.service;

import com.alibaba.fastjson.JSON;
import com.example.bigData.dto.SensorDataReq;
import com.example.bigData.entity.SensorData;
import com.example.bigData.mapper.SensorDataMapper;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaService {

    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Autowired
    private SensorDataMapper sensorDataMapper;
    public void publish(String payload) {

        // 1. 配置生产者参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // 序列化, Java字符串对象转换为字节数组以便网络传输
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 2. 创建生产者实例
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 3. 构造消息记录
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, payload);

        // 4. 异步发送消息
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.printf("消息已发布 - value =%s partition=%d, offset=%d%n",
                        payload, metadata.partition(), metadata.offset());
            } else {
                exception.printStackTrace();
            }
        });

        // 5. 关闭生产者
        producer.close();

    }

    public void consumer() {
        // 1. 配置消费者参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "remote-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
        // 2. 创建消费者实例
        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 3. 订阅主题
            consumer.subscribe(Collections.singleton(topic));

            // 4. 消费消息循环
            while (true) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("消息已消费: topic=%s, partition=%d, offset=%d, key=%s, value=%s%n",
                            record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());
                    SensorDataReq sensorDataReq = JSON.parseObject(new String(record.value()), SensorDataReq.class);
                    SensorData sensorData = SensorData.buildBy(sensorDataReq);
                    sensorDataMapper.insert(sensorData);
                }

                // 5. 异步提交offset
                consumer.commitAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
