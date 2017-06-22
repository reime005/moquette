package io.moquette.server;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import io.moquette.interception.HazelcastConnectMsg;
import io.moquette.parser.proto.messages.ConnectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mackristof on 28/05/2016.
 */
public class HazelcastConnectListener implements MessageListener<HazelcastConnectMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(HazelcastConnectListener.class);

    private final Server server;

    public HazelcastConnectListener(Server server){
        this.server = server;
    }

    @Override
    public void onMessage(Message<HazelcastConnectMsg> msg) {
        try {
            if (!msg.getPublishingMember().equals(server.getHazelcastInstance().getCluster().getLocalMember())) {
                HazelcastConnectMsg hzMsg = msg.getMessageObject();
                LOG.info("{} connected from hazelcast", hzMsg.getClientId());
                ConnectMessage connectMessage = new ConnectMessage();
                connectMessage.setClientID(hzMsg.getClientId());
                connectMessage.setUsername(hzMsg.getUsername());
                server.internalConnect(connectMessage, hzMsg.getIpPortMessage());
            }
        } catch (Exception ex) {
            LOG.error("error polling hazelcast msg queue", ex);
        }
    }
}
