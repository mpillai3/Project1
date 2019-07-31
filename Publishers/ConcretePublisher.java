package publishers;

import events.AbstractEvent;
import strategies.publisher.IStrategy;
import strategies.publisher.StrategyFactory;


/**
 * @author kkontog, ktsiouni, mgrigori
 *
 * the WeatherPublisher class is an example of a ConcretePublisher
 * implementing the IPublisher interface. Of course the publish
 * methods could have far more interesting logics
 */
public class ConcretePublisher extends AbstractPublisher {

	private long pubID = PubIDgen.getNewPubID();

	/**
	 * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
	 * method
	 */
	protected ConcretePublisher(IStrategy concreteStrategy) {
		this.publishingStrategy = concreteStrategy;
		System.out.println("Publisher " + pubID + " created");
		System.out.println("Publisher has strategy " + concreteStrategy.getClass().getSimpleName());
	}

	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish(events.AbstractEvent)
	 */
	@Override
	public void publish(AbstractEvent event) {
		//System.out.println("Publisher " + pubID + " publishes event " + event.getEventType());
		publishingStrategy.doPublish(event, pubID);
	}

	/* (non-Javadoc)
	 * @see publishers.IPublisher#publish()
	 */
	@Override
	public void publish() {
		publishingStrategy.doPublish(pubID);
	}
	
	@Override
	public long getID() {
		return pubID;
	}

}
