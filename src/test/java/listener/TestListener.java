package listener;

import com.phakel.event.BaseEvent;
import com.phakel.event.Listener;
import com.phakel.event.Priority;
import event.TestEvent;
import org.apache.log4j.Logger;

/**
 * @author EvanLuo42
 */
public class TestListener implements BaseEvent {
    private final Logger logger = Logger.getLogger(TestListener.class);
    public String eventName = "";

    @Listener(event = TestEvent.class, priority = Priority.MONITOR)
    public void onTestEvent(TestEvent testEvent) {
        eventName = testEvent.name;
    }
}
