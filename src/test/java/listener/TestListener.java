package listener;

import com.phakel.event.Listener;
import com.phakel.event.Subscribe;
import com.phakel.event.Priority;
import event.TestEvent;
import org.apache.log4j.Logger;

/**
 * @author EvanLuo42
 */
public class TestListener implements Listener {
    public String eventName = "";

    @Subscribe(priority = Priority.HIGH)
    public void onTestEvent(TestEvent testEvent) {
        eventName = testEvent.name;
    }
}
