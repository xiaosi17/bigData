package demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.common.serialization.StringSerializer;
public class KafkaProducerDemo {
    private static final String TOPIC = "test";
    private static final String BOOTSTRAP_SERVERS = "14.22.77.247:9092";

    public static void main(String[] args) {
        // 1. 配置生产者参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 2. 创建生产者实例
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 3. 生成测试数据
        for (int i = 101; i < 105; i++) {
            String key = "key-" + i;
            String value = "message-" + i;

            // 4. 构造消息记录
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(TOPIC, key, value);

            // 5. 异步发送消息
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Sent record(key=%s value=%s) to partition=%d, offset=%d%n",
                            key, value, metadata.partition(), metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });
        }

        // 6. 关闭生产者
        producer.close();
    }
}
