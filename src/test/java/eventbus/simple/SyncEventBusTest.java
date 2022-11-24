package eventbus.simple;

import com.phakel.EventBusFactory;
import com.phakel.config.EventBusConfig;
import com.phakel.eventbus.EventBus;
import event.TestEvent;
import listener.TestListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author EvanLuo42
 */
public class SyncEventBusTest {
    private static EventBus eventBus;

    private static TestListener testListener;

    @BeforeAll
    static void initEventBus() {
        var eventBusConfig = new EventBusConfig(EventBusConfig.EventBusType.SYNC, "eventbus.simple.SimpleEventBusTest");
        eventBus = EventBusFactory.createEventBusFromConfig(eventBusConfig);
        testListener = new TestListener();
    }

    @Test
    void testBroadcastEvent1() {
        eventBus.registerListener(testListener);
        var testEvent = new TestEvent();
        eventBus.broadcast(testEvent);
        assertEquals(testEvent.name, testListener.eventName);
    }
}
