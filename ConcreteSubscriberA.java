package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import publishers.PubIDgen;
import states.subscriber.IState;
import states.subscriber.StateFactory;
import states.subscriber.StateName;


/**
 * @author kkontog, ktsiouni, mgrigori
 * an example concrete subscriber
 */
class ConcreteSubscriberA extends AbstractSubscriber {

	private long subID = SubIDgen.getNewSubID();

	protected ConcreteSubscriberA(StateName stateName) {
		state = StateFactory.createState(stateName);
		System.out.println("Subscriber " + subID + " created");
		System.out.println("Subscriber is on state " + state.getClass().getSimpleName());
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#setState(states.subscriber.StateName)
	 */
	public void setState(StateName stateName) {
		state = StateFactory.createState(stateName);
		System.out.println("Subscriber " + subID + " set to state " + stateName.getClass().getSimpleName());
	}


	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#alert(events.AbstractEvent, java.lang.String)
	 */
	@Override
	public void alert(AbstractEvent event, String channelName) {
		System.out.println("Subscriber " + this.subID + " handling event " + event.getEventType() + " published on channel " + channelName);
		state.handleEvent(event, channelName);
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#subscribe(java.lang.String)
	 */
	@Override
	public void subscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#unsubscribe(java.lang.String)
	 */
	@Override
	public void unsubscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);

	}
	
	@Override
	public long getID() {
		return subID;
	}


}
