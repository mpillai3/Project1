package strategies.publisher;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import pubSubServer.ChannelEventDispatcher;

import java.util.ArrayList;
import java.util.List;

public class DefaultStrategy implements IStrategy {

    @Override
    public void doPublish(long publisherId) {
        doPublish(EventFactory.createEvent(EventType.TypeA, publisherId, new EventMessage("header", "body")), publisherId);
    }

    @Override
    public void doPublish(AbstractEvent event, long publisherId) {
        List<String> toPublishTo = new ArrayList<>();
        for(AbstractChannel channel : ChannelDiscovery.getInstance().listChannels()) {
            toPublishTo.add(channel.getChannelTopic());
        }

        System.out.println("Publisher " + event.getEventPublisher() + " publishes event " + event.getEventType());
        ChannelEventDispatcher.getInstance().postEvent(event, toPublishTo);
    }

}
