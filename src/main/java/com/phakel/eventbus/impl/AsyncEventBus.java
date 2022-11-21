package com.phakel.eventbus.impl;

import com.phakel.config.EventBusConfig;
import com.phakel.eventbus.EventBus;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * An asynchronous EventBus implementation.
 * @author EvanLuo42
 */
public class AsyncEventBus implements EventBus {
    private final String name;

    /**
     * The constructor of AsyncEventBus.
     * @param config EventBus Config.
     */
    public AsyncEventBus(EventBusConfig config) {
        this.name = config.name();
    }

    @Override
    public void registerListener(Object listener) {

    }

    @Override
    public void unregisterListener(Object listener) {

    }

    @Override
    public Map<Method, Object> getAllListeners() {
        return null;
    }

    @Override
    public void broadcast(Object event) {

    }
}
