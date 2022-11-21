package com.phakel.config;

/**
 * EventBus Config
 * @author EvanLuo42
 * @param type EventBus Type
 * @see EventBusType
 * @param name EventBus Name
 */
public record EventBusConfig(EventBusType type, String name) {
    /**
     * All supported type of EventBus.
     */
    public enum EventBusType {
        /**
         * The EventBus is synchronous.
         */
        SYNC,

        /**
         * The EventBus is asynchronous
         */
        ASYNC
    }
}
