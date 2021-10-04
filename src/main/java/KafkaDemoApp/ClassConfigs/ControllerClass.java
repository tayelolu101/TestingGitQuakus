package KafkaDemoApp.ClassConfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController("/Kafta")
public class ControllerClass {

    private final Message message;
    private final KafkaService kafkaService;

    @Autowired
    public ControllerClass(Message message, KafkaProducerConfig kafkaProducerConfig, KafkaService kafkaService) {
        this.message = message;
        this.kafkaService = kafkaService;

    }

    @GetMapping (value = "messages/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String GetKafkaMessage(@PathVariable("message") String messages) throws Exception {
        try {
            message.setMssg(messages);
            kafkaService.SendMessage(message);

        }catch (Exception ex){
            throw new Exception("Error with request");
        }
        return "Message Successfully posted";
    }
}
