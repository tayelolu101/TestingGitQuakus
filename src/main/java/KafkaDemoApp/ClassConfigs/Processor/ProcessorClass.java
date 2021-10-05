package KafkaDemoApp.ClassConfigs.Processor;

import KafkaDemoApp.ClassConfigs.Message;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
public class ProcessorClass {
private  final Logger log = LoggerFactory.getLogger(ProcessorClass.class);
    @Bean
    public Function<KStream<String, Message>, KStream<String, Message>> streamProcessor(){

        return  kstream -> kstream.filter((key, message) -> {
            if(message.isDead()){
                log.info("Inactive Domain "+ message.getMssg());
            }else{
                log.info("Active Domain "+ message.getMssg());
            }
            return  !message.isDead();
        });
    }
}
