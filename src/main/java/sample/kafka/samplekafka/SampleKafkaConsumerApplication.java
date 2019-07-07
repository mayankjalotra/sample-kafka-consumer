package sample.kafka.samplekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.kafka.consumer.MessageConsumerService;

@SpringBootApplication(scanBasePackages = {"sample.kafka"})
public class SampleKafkaConsumerApplication implements CommandLineRunner{

	@Autowired
	private MessageConsumerService msgConsumer;
	
	public static void main(String[] args) {
		SpringApplication.run(SampleKafkaConsumerApplication.class, args);
	}
	@Override
    public void run(String... args) {
		msgConsumer.consume();
	}
	
}
