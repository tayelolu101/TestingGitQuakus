package KafkaDemoApp.ClassConfigs;

import org.springframework.stereotype.Component;

@Component
public class Message {

    private String mssg;
    private  boolean isDead;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

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
