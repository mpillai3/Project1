package pubSubServer;
import java.util.Collection;

import publishers.AbstractPublisher;
import subscribers.AbstractSubscriber;
import events.AbstractEvent;
import events.EventMessage;
import events.EventFactory;
import java.util.HashMap;

import Actions.Nullable;
import events.EventType;

public class Commands {

	private HashMap<Object, AbstractSubscriber> subscribers;
    private HashMap<Object, AbstractPublisher> publishers;
    
	public Commands(Collection<AbstractSubscriber> subs, Collection<AbstractPublisher> pubs) {
		subscribers = new HashMap<>();
		publishers = new HashMap<>();
		
		for (AbstractSubscriber sub : subs)
			subscribers.put(sub.getID(), sub);
		for (AbstractPublisher pub : pubs)
			publishers.put(pub.getID(), pub);
	}
	
	private AbstractSubscriber getSub(long subID) {
		return subscribers.get(subID);
	}
	private AbstractPublisher getPub(long pubID) {
		return publishers.get(pubID);
	}
	
	public void pub(Long pubID, @Nullable EventType eventType, @Nullable String header, @Nullable String body) {
		AbstractPublisher pub = getPub(pubID);
		//EventMessage payload = new EventMessage(header, body);
		//AbstractEvent event = EventFactory.createEvent(eventType, pubID, payload);
		pub.publish(EventFactory.createEvent(eventType, pubID, header == null ? null : new EventMessage(header, body)));
	}
	
	public void sub(Long subID, String channel) {
		AbstractSubscriber sub = getSub(subID);
		sub.subscribe(channel);
	}
	
	public void block(Long subID, String channel) {
		AbstractSubscriber sub = getSub(subID);
		ChannelAccessControl.getInstance().blockSubcriber(sub, channel);
	}
	
	public void unBlock(Long subID, String channel) {
		AbstractSubscriber sub = getSub(subID);
		ChannelAccessControl.getInstance().unBlockSubscriber(sub, channel);
	}
}
