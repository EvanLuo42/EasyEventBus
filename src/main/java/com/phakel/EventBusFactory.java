package com.phakel;

import com.phakel.config.EventBusConfig;
import com.phakel.eventbus.EventBus;
import com.phakel.eventbus.impl.AsyncEventBus;
import com.phakel.eventbus.impl.SyncEventBus;

/**
 * Factory for producing EventBus from config.
 * @author EvanLuo42
 */
public class EventBusFactory {
    /**
     * Get EventBus Instance from a custom config.
     * @param config EventBus Config
     * @return A new EventBus Instance
     */
    public static EventBus createEventBusFromConfig(EventBusConfig config) {
        return switch (config.type()) {
            case SYNC -> new SyncEventBus(config);
            case ASYNC -> new AsyncEventBus(config);
        };
    }

    /**
     * Get a default EventBus.
     * @return A new EventBus Instance.
     */
    public static EventBus getDefaultEventBus() {
        return new SyncEventBus(new EventBusConfig(EventBusConfig.EventBusType.SYNC,
                "Default"));
    }
}
