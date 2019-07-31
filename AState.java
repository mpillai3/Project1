package states.subscriber;

import events.AbstractEvent;

public class AState implements IState {

    @Override
    public void handleEvent(AbstractEvent event, String channelName) {
        System.out.println("Received event " + event.getEventType() + " from " + channelName + " handled at state A");
    }
}
