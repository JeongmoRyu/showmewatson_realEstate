package com.watson.noticeconsumer;

import com.watson.noticeconsumer.configure.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {KafkaProperties.class})
public class NoticeConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeConsumerApplication.class, args);
	}

}
