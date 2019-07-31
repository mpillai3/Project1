package states.subscriber;

import events.AbstractEvent;

public class CState implements IState {

    @Override
    public void handleEvent(AbstractEvent event, String channelName) {
        System.out.println("Received event " + event.getEventType() + " from " + channelName + " handled at state C");
    }

}
