package io.moquette.server;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import io.moquette.interception.HazelcastDisconnectMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mackristof on 28/05/2016.
 */
public class HazelcastDisconnectListener implements MessageListener<HazelcastDisconnectMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(HazelcastDisconnectListener.class);

    private final Server server;

    public HazelcastDisconnectListener(Server server){
        this.server = server;
    }

    @Override
    public void onMessage(Message<HazelcastDisconnectMsg> msg) {
        try {
            if (!msg.getPublishingMember().equals(server.getHazelcastInstance().getCluster().getLocalMember())) {
                HazelcastDisconnectMsg hzMsg = msg.getMessageObject();
                LOG.info("{} disconnected from hazelcast", hzMsg.getClientId());
                server.internalDisconnect(hzMsg.getClientId(), hzMsg.getUserName());
            }
        } catch (Exception ex) {
            LOG.error("error polling hazelcast msg queue", ex);
        }
    }
}
