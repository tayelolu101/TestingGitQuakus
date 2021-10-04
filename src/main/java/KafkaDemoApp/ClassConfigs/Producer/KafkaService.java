package KafkaDemoApp.ClassConfigs.Producer;

import KafkaDemoApp.ClassConfigs.KafkaData;
import KafkaDemoApp.ClassConfigs.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KafkaService {
    @Value("${message.topic.name}")
    private String Topic;
    @Value("${message.url.add}")
    private final String url = "";
    private KafkaTemplate<String, Message> kafkaTemplate;

    private static Logger log = LoggerFactory.getLogger("KafkaService");

    public KafkaService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendMessage(Message mssg) throws Exception {

        try {
            Mono<KafkaData> dataMonoList = WebClient.create()
                    .get()
                    .uri(url.replace("", mssg.getMssg()))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(KafkaData.class);

            dataMonoList.subscribe(kafkadatas -> {
                kafkadatas.data
                        .forEach(message -> {
                            kafkaTemplate.send(Topic, message);
                            log.info("Published first Message : {}, to topic : {}", mssg.getMssg(), Topic);
                        });

                });

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
