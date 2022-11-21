package eventbus.simple;

import com.phakel.EventBusFactory;
import com.phakel.config.EventBusConfig;
import com.phakel.event.Listener;
import com.phakel.eventbus.EventBus;
import event.TestEvent;
import listener.TestListener;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author EvanLuo42
 */
public class SyncEventBusTest {
    private static EventBus eventBus;
    private static TestListener testListener;
    private final Logger logger = Logger.getLogger(SyncEventBusTest.class);

    @BeforeAll
    static void initEventBus() {
        var eventBusConfig = new EventBusConfig(EventBusConfig.EventBusType.SYNC, "eventbus.simple.SimpleEventBusTest");
        eventBus = EventBusFactory.createEventBusFromConfig(eventBusConfig);
        testListener = new TestListener();
    }

    @Test
    void testRegisterListener1() {
        eventBus.registerListener(testListener);
        Arrays.stream(testListener.getClass().getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(Listener.class)) {
                assertTrue(eventBus.getAllListeners().containsKey(method));
            }
        });
    }

    @Test
    void testBroadcastEvent2() {
        var testEvent = new TestEvent();
        eventBus.broadcast(testEvent);
        assertEquals(testEvent.name, testListener.eventName);
    }

    @Test
    void testPriority3() {

    }
}
