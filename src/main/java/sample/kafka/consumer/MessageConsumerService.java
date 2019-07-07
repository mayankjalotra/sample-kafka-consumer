package sample.kafka.consumer;


import java.util.Collections;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.kafka.config.KafkaConsumerConfig;

@Service
public class MessageConsumerService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);
	
	@Autowired
	private KafkaConsumerConfig consumerConfig;
	
	private String topicName;
	
	KafkaConsumer<String, String> consumer;
	
	@PostConstruct
	private void init() {
		Properties consumerProperties = consumerConfig.getConsumerProperties();
		this.topicName = consumerConfig.getTopicName();
		this.consumer = new KafkaConsumer<>(consumerProperties);
		this.consumer.subscribe(Collections.singleton(this.topicName));
	}

	public void consume() {
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for(ConsumerRecord<String, String> record : records) {
				System.out.println("=========Consumed Messages ==========");
				System.out.println(record.value()+"::"+record.partition()+"::"+record.offset());
			}
		}
	}
	
	
}
