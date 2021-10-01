package KafkaDemoApp.ClassConfigs;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Component
public class KafkaProducerConfig {

    private static final Logger log = LoggerFactory.getLogger("KafkaProducerConfig");

    @Value("${spring.kafka.bootstrap-servers}")
    private String serverAddress;
    @Value("${message.topic.name}")
    private String Topic;
    @Autowired
    private Message message;

    @Bean
    public ProducerFactory<String, String> producerFactory(){

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        configProps.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkatemplate(){

        /*Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        configProps.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory(configProps);
        return new KafkaTemplate<>(defaultKafkaProducerFactory);*/
        return new KafkaTemplate<>(producerFactory());
    }

    public void SendMessage(Message mssg) throws Exception {
        String ApiMessage = message.getPostedMessage(mssg.getMssg());
        kafkatemplate().send(Topic, ApiMessage);
        log.info("Published first Message to topic : {}", Topic);
    }
}
