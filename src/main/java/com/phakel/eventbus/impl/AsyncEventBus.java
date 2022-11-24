package com.phakel.eventbus.impl;

import com.phakel.config.EventBusConfig;
import com.phakel.event.BaseEvent;
import com.phakel.event.Listener;
import com.phakel.eventbus.EventBus;

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
    public void registerListener(Listener listener) {

    }

    @Override
    public void unregisterListener(Listener listener) {

    }

    @Override
    public void broadcast(BaseEvent event) {

    }
}
