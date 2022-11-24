package com.phakel.eventbus;

import com.phakel.event.BaseEvent;
import com.phakel.event.Listener;

/**
 * An Interface for EventBus. <br/>
 * All EventBus should implement this Interface.
 * @author EvanLuo42
 */
public interface EventBus {
    /**
     * Register a listener
     * @param listener Listener waiting for registering.
     */
    void registerListener(Listener listener);

    /**
     * Unregister a listener.
     * @param listener Listener waiting for unregistering.
     */
    void unregisterListener(Listener listener);

    /**
     * Broadcast an event to all listeners.
     * @param event Event waiting for broadcasting.
     */
    void broadcast(BaseEvent event);
}
