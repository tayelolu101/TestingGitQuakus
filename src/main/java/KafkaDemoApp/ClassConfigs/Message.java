package KafkaDemoApp.ClassConfigs;

import org.springframework.stereotype.Component;

@Component
public class Message {

    private String mssg;

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public String getPostedMessage(String postedMessage){
        return postedMessage;
    }
}
