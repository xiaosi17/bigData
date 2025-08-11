package demo.kafka;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo {
    private static final String TOPIC = "test";
    private static final String BOOTSTRAP_SERVERS = "14.22.77.247:9092";

    public static void main(String[] args) {
        // 1. 配置消费者参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
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
            consumer.subscribe(Collections.singleton(TOPIC));

            // 4. 消费消息循环
            while (true) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumed: topic=%s, partition=%d, offset=%d, key=%s, value=%s%n",
                            record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());
                }

                // 5. 异步提交offset
                consumer.commitAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

