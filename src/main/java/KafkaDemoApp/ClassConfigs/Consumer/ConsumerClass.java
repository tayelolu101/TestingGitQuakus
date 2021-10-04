package KafkaDemoApp.ClassConfigs.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerClass {

    private final Logger log = LoggerFactory.getLogger(ConsumerClass.class);

    @KafkaListener(topics = "${message.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void KafkaListener(String Msg){
        log.info("Received  Message in group : {}", Msg);
    }

}
