package io.moquette.parser.proto.messages;

import io.netty.channel.Channel;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * Created by Marius Reimer on 18/02/17.
 */
public class ConnectIpPortMessage implements Serializable {

    private String remoteAddress;
    private String localAddress;
    private int remotePort;
    private int localPort;

    public ConnectIpPortMessage(String remoteAddress, String localAddress, int remotePort, int localPort) {
        this.remoteAddress = remoteAddress;
        this.localAddress = localAddress;
        this.remotePort = remotePort;
        this.localPort = localPort;
    }

    public static ConnectIpPortMessage asChannel(Channel channel) {
        if (channel.remoteAddress() instanceof InetSocketAddress && channel.localAddress() instanceof InetSocketAddress) {
            final String remoteAddress = ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
            final int remotePort = ((InetSocketAddress) channel.remoteAddress()).getPort();

            final String localAddress = ((InetSocketAddress) channel.localAddress()).getAddress().getHostAddress();
            final int localPort = ((InetSocketAddress) channel.localAddress()).getPort();

            return new ConnectIpPortMessage(remoteAddress, localAddress, remotePort,  localPort);
        }

        return new ConnectIpPortMessage("0.0.0.0", "0.0.0.0", 0, 0);
    }

    public static ConnectIpPortMessage asString(String value) throws Exception {
        String remoteAddress = "";
        String localAddress = "";
        int remotePort = 0;
        int localPort = 0;

        final String[] result = value.split(" ");

        if (result.length != 13) {
            return null;
        }

        remoteAddress = result[3];
        remotePort = Integer.valueOf(result[6]);
        localAddress = result[9];
        localPort = Integer.valueOf(result[12]);

        return new ConnectIpPortMessage(remoteAddress, localAddress, remotePort, localPort);
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    @Override
    public String toString() {
        return super.toString() + " | " +
                "remoteAddress: " + remoteAddress + " | " +
                "remotePort: " + remotePort + " | " +
                "localAddress: " + localAddress + " | " +
                "localPort: " + localPort;
    }
}
