package com.example.bigData.service;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FlinkService {

    @Value("${flink.flink-cluster-host}")
    private String flinkClusterHost;
    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    public void consumer() throws Exception {
        // 1. 创建流执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment
                .createRemoteEnvironment(
                        flinkClusterHost, // 集群JobManager地址
                        8081,           // REST端口
                        "H:\\java\\bigData\\target\\bigData-1.0.0.jar" // 程序JAR包路径
                );

        // 设置并行度
        env.setParallelism(1);

//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. 配置Kafka源
//        KafkaSource<String> source = KafkaSource.<String>builder()
//                .setBootstrapServers(bootstrapServers)
//                .setTopics(topic)
//                .setGroupId("flink-consumer-group")
//                .setStartingOffsets(OffsetsInitializer.earliest())
//                .setValueOnlyDeserializer(new SimpleStringSchema())
//                .build();
//
//        // 3. 设置检查点(实现Exactly-Once语义)
//        env.enableCheckpointing(5000); // 5秒间隔
//
//        // 4. 创建数据流
//        DataStream<String> stream = env.fromSource(
//                source,
//                WatermarkStrategy.noWatermarks(),
//                "Kafka Source"
//        );
        // 测试用例
       DataStream<String> stream = env.fromElements("A", "b", "C");

        // 5. 数据处理逻辑
//        stream.map(value -> {
//            // 业务处理示例
//            System.out.println("Received: " + value);
//            return "Processed: " + value;
//        })
//                .print();
       stream.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) {
                return value.toUpperCase(); // 示例处理逻辑
            }
        }).print();
        // 6. 将处理后的数据写入Doris


        // 7. 打印到控制台(调试用)
        stream.print();

        // 8. 启动作业
        env.execute("Flink Kafka Consumer Job");
    }
}
