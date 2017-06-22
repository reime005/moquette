package io.moquette.server;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import io.moquette.interception.HazelcastConnLostMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mackristof on 28/05/2016.
 */
public class HazelcastConnLostListener implements MessageListener<HazelcastConnLostMsg> {
    private static final Logger LOG = LoggerFactory.getLogger(HazelcastConnLostListener.class);

    private final Server server;

    public HazelcastConnLostListener(Server server){
        this.server = server;
    }

    @Override
    public void onMessage(Message<HazelcastConnLostMsg> msg) {
        try {
            if (!msg.getPublishingMember().equals(server.getHazelcastInstance().getCluster().getLocalMember())) {
                HazelcastConnLostMsg hzMsg = msg.getMessageObject();
                LOG.info("{} connection lost from hazelcast", hzMsg.getClientId());
                server.internalConnectionLost(hzMsg.getClientId(), hzMsg.getUserName());
            }
        } catch (Exception ex) {
            LOG.error("error polling hazelcast msg queue", ex);
        }
    }
}
