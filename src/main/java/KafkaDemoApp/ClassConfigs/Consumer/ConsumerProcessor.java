package KafkaDemoApp.ClassConfigs.Consumer;

import KafkaDemoApp.ClassConfigs.Message;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConsumerProcessor {
    @Value("${message.topic.name}")
    private String Topic;
    private final Logger log = LoggerFactory.getLogger(ConsumerProcessor.class);
    @Bean
    public Consumer<KStream<String, Message>> consumerService(){

        return kstream -> kstream.foreach((key, message) ->{
            log.info("Consumed message : {} from Topic {}", message, Topic);
        });
    }
}
