package io.moquette.interception.messages;

import io.moquette.parser.proto.messages.ConnectIpPortMessage;
import io.moquette.parser.proto.messages.ConnectMessage;

/**
 * @author Wagner Macedo
 */
public class InterceptConnectMessage extends InterceptAbstractMessage {
    private final ConnectMessage msg;
    private final ConnectIpPortMessage ipPortMessage;

    public InterceptConnectMessage(ConnectMessage msg, ConnectIpPortMessage ipPortMessage) {
        super(msg);
        this.msg = msg;
        this.ipPortMessage = ipPortMessage;
    }

    public String getClientID() {
        return msg.getClientID();
    }

    public boolean isCleanSession() {
        return msg.isCleanSession();
    }

    public int getKeepAlive() {
        return msg.getKeepAlive();
    }

    public boolean isPasswordFlag() {
        return msg.isPasswordFlag();
    }

    public byte getProtocolVersion() {
        return msg.getProtocolVersion();
    }

    public String getProtocolName() {
        return msg.getProtocolName();
    }

    public boolean isUserFlag() {
        return msg.isUserFlag();
    }

    public boolean isWillFlag() {
        return msg.isWillFlag();
    }

    public byte getWillQos() {
        return msg.getWillQos();
    }

    public boolean isWillRetain() {
        return msg.isWillRetain();
    }

    public String getUsername() {
        return msg.getUsername();
    }

    public byte[] getPassword() {
        return msg.getPassword();
    }

    public String getWillTopic() {
        return msg.getWillTopic();
    }

    public byte[] getWillMessage() {
        return msg.getWillMessage();
    }

    public ConnectIpPortMessage getIpPortMessage() {
        return ipPortMessage;
    }
}
