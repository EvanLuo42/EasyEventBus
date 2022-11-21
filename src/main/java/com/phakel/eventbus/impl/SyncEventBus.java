package com.phakel.eventbus.impl;

import com.phakel.config.EventBusConfig;
import com.phakel.event.Listener;
import com.phakel.eventbus.EventBus;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A synchronous EventBus implementation.
 * @author EvanLuo42
 */
public class SyncEventBus implements EventBus {
    /**
     * EventBus Name
     */
    public String name;
    private static final Logger logger = Logger.getLogger(SyncEventBus.class);

    /**
     * Map of Listeners
     */
    private final Map<Method, Object> listeners = new ConcurrentHashMap<>();

    /**
     * Constructor for SyncEventBus.
     * @param config EventBus Config
     */
    public SyncEventBus(EventBusConfig config) {
        this.name = config.name();
        logger.debug(SyncEventBus.class.getName() + " created");
    }

    @Override
    public void registerListener(Object listener) {
        Arrays.stream(listener.getClass().getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(Listener.class)) {
                listeners.put(method, listener);
                logger.debug(method.getName() + " registered");
            }
        });
    }

    @Override
    public void unregisterListener(Object listener) {
        Arrays.stream(listener.getClass().getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(Listener.class)) {
                listeners.remove(method);
                logger.debug(method.getName() + " unregistered");
            }
        });
    }

    @Override
    public Map<Method, Object> getAllListeners() {
        return listeners;
    }

    @Override
    public void broadcast(Object event) {
        listeners
                .entrySet()
                .stream()
                .sorted(
                        // Ascending sort the Method Handles by their priority.
                        Comparator.comparingInt(o ->
                                o.getKey()
                                        .getAnnotation(Listener.class)
                                        .priority()
                                        .ordinal()
                        )
                )
                .forEach(method -> {
                    try {
                        // Invoke all Method Handles
                        method.getKey().invoke(method.getValue(), event);
                    } catch (Exception e) {
                        logger.error(e.getCause());
                    }
                });
    }
}
