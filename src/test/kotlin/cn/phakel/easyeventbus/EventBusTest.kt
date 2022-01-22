package cn.phakel.easyeventbus

import cn.phakel.easyeventbus.event.TestEvent
import cn.phakel.easyeventbus.listener.EventBus
import cn.phakel.easyeventbus.listener.Listener
import cn.phakel.easyeventbus.listener.ListenerPriority
import cn.phakel.easyeventbus.listener.Subscribe
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EventBusTest: Listener {

    private var test = false

    @Test
    fun postEvent() {
        EventBus.get.registerListener(EventBusTest())
        EventBus.get.post(TestEvent(true))
        assertTrue(test)
    }

    @Subscribe(event = TestEvent::class, priority = ListenerPriority.HIGH)
    fun receiveEvent(event: TestEvent) {
        this.test = event.test
    }
}