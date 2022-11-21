package com.phakel.eventbus;

import java.lang.reflect.Method;
import java.util.Map;

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
    void registerListener(Object listener);

    /**
     * Unregister a listener.
     * @param listener Listener waiting for unregistering.
     */
    void unregisterListener(Object listener);

    /**
     * Return all registered listener.
     * @return All listeners registered.
     */
    Map<Method, Object> getAllListeners();

    /**
     * Broadcast an event to all listeners.
     * @param event Event waiting for broadcasting.
     */
    void broadcast(Object event);
}
