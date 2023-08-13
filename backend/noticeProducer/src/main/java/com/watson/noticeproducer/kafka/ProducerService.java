package com.watson.noticeproducer.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ProducerService {
    private final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    public void sendToBroker(String topicName, String multicastMessage) {
        Properties configs = new Properties();
        String bootStrapServers = "kafka1:9091, kafka2:9092, kafka3:9093";
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);
        logger.info("{}", multicastMessage);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, multicastMessage);
        producer.send(producerRecord);
        logger.info("{}", producerRecord);
        producer.flush();
        producer.close();
    }
}
