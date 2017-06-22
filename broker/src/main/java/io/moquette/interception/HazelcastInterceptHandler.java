package io.moquette.interception;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import io.moquette.BrokerConstants;
import io.moquette.interception.messages.InterceptConnectMessage;
import io.moquette.interception.messages.InterceptConnectionLostMessage;
import io.moquette.interception.messages.InterceptDisconnectMessage;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mackristof on 28/05/2016.
 */
public class HazelcastInterceptHandler extends AbstractInterceptHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastInterceptHandler.class);
    private final HazelcastInstance hz;

    public HazelcastInterceptHandler(Server server){
        this.hz = server.getHazelcastInstance();
    }

    @Override
    public void onPublish(InterceptPublishMessage msg) {
//        LOG.info("{} publish on {} message: {}", msg.getClientID(), msg.getTopicName(), new String(msg.getPayload().array()));
//        if (msg.getTopicName().equals("car/video/")) {
//            ITopic<HazelcastMsg> topic = hz.getTopic(BrokerConstants.HAZELCAST_TOPIC_PUBLISH);
//            HazelcastMsg hazelcastMsg = new HazelcastMsg(msg);
//            topic.publish(hazelcastMsg);
//        }
    }

    @Override
    public void onConnect(InterceptConnectMessage msg) {
        LOG.info("{} client {} connected: {}", msg.getClientID(), msg.getIpPortMessage().toString());
        ITopic<HazelcastConnectMsg> topic = hz.getTopic(BrokerConstants.HAZELCAST_TOPIC_AUTH_CONNECT);
        HazelcastConnectMsg hazelcastMsg = new HazelcastConnectMsg(msg);
        topic.publish(hazelcastMsg);
    }

    @Override
    public void onDisconnect(InterceptDisconnectMessage msg) {
        LOG.info("{} disconnected", msg.getClientID());
        ITopic<HazelcastDisconnectMsg> topic = hz.getTopic(BrokerConstants.HAZELCAST_TOPIC_AUTH_DISCONNECT);
        HazelcastDisconnectMsg hazelcastMsg = new HazelcastDisconnectMsg(msg);
        topic.publish(hazelcastMsg);
    }

    @Override
    public void onConnectionLost(InterceptConnectionLostMessage msg) {
        LOG.info("{} onConnectionLost", msg.getClientID());
        ITopic<HazelcastConnLostMsg> topic = hz.getTopic(BrokerConstants.HAZELCAST_TOPIC_AUTH_CONN_LOST);
        HazelcastConnLostMsg hazelcastMsg = new HazelcastConnLostMsg(msg);
        topic.publish(hazelcastMsg);
    }
}
