package KafkaDemoApp.ClassConfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController("/Kafta")
public class ControllerClass {

    private final Message message;
    private final KafkaProducerConfig kafkaProducerConfig;

    @Autowired
    public ControllerClass(Message message, KafkaProducerConfig kafkaProducerConfig) {
        this.message = message;
        this.kafkaProducerConfig = kafkaProducerConfig;
    }

    @PostMapping (value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String GetKafkaMessage(@PathVariable("messages") String messages) throws Exception {
        try {
            message.setMssg(messages);
            kafkaProducerConfig.SendMessage(message);

        }catch (Exception ex){
            throw new Exception("Error with request");
        }
        return "Message Successfully posted";
    }
}
