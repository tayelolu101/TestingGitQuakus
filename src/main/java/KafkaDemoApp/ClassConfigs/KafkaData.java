package KafkaDemoApp.ClassConfigs;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaData {
    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

    public List<Message> data;

}
