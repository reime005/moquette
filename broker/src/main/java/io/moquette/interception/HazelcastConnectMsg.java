package io.moquette.interception;

import io.moquette.interception.messages.InterceptConnectMessage;
import io.moquette.parser.proto.messages.ConnectIpPortMessage;

import java.io.Serializable;

/**
 * Created by Marius Reimer on
 */
public class HazelcastConnectMsg implements Serializable {

    private final String clientId;
    private final String username;
    private final ConnectIpPortMessage ipPortMessage;

    public HazelcastConnectMsg(InterceptConnectMessage msg) {
        this.clientId = msg.getClientID();
        this.username = msg.getUsername();
        this.ipPortMessage = msg.getIpPortMessage();
    }

    public String getClientId() {
        return clientId;
    }

    public String getUsername() {
        return username;
    }

    public ConnectIpPortMessage getIpPortMessage() {
        return ipPortMessage;
    }
}
