package io.moquette.interception;

import io.moquette.interception.messages.InterceptConnectionLostMessage;

import java.io.Serializable;

/**
 * Created by Marius Reimer on
 */
public class HazelcastConnLostMsg implements Serializable {

    private final String clientId;
    private String userName;

    public HazelcastConnLostMsg(InterceptConnectionLostMessage msg) {
        this.clientId = msg.getClientID();
        this.userName = msg.getUsername();
    }

    public String getClientId() {
        return clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
