package KafkaDemoApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@Async
@SpringBootApplication
public class KafkaDemoMain{
    private static final Logger LOG = LoggerFactory.getLogger("KafkaDemoApp.KafkaDemoMain");

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoMain.class);
    }

}
