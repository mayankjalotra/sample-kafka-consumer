package sample.kafka.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties" ,ignoreResourceNotFound = true)
public class KafkaConsumerConfig {
	
	@Value("${kafka.consumer.bootstrapServers}")
	private String bootstrapServers;
	
	@Value("${kafka.consumer.topicName}")
	private String topicName;

	@Value("${kafka.consumer.groupId}")
	private String groupId;
	
	
	public Properties getConsumerProperties() {
		Properties consumerProperties = new Properties();
		consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG , "latest");
		consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 10000);
		consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return consumerProperties;
	}
	
	public String getTopicName() {
		return this.topicName;
	}
}
