package io.moquette.interception;

import io.moquette.interception.messages.InterceptDisconnectMessage;

import java.io.Serializable;

/**
 * Created by Marius Reimer on 12/02/17.
 */
public class HazelcastDisconnectMsg implements Serializable {

    private final String clientId;
    private String userName;

    public HazelcastDisconnectMsg(InterceptDisconnectMessage msg) {
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
