package com.phakel.eventbus.impl;

import com.phakel.config.EventBusConfig;
import com.phakel.event.*;
import com.phakel.eventbus.EventBus;
import org.apache.log4j.Logger;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
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

    private final MethodHandles.Lookup lookup = MethodHandles.publicLookup();

    /**
     * Map of Listeners
     */
    private final Map<MethodHandle, Priority> listeners = new ConcurrentHashMap<>();

    /**
     * Constructor for SyncEventBus.
     * @param config EventBus Config
     */
    public SyncEventBus(EventBusConfig config) {
        this.name = config.name();
        logger.info("EventBus " + this.name + " created");
    }

    @Override
    public void registerListener(Listener listener) {
        var methodsStream = Arrays.stream(listener.getClass().getMethods());

        methodsStream.forEach(method -> {
            if (method.isAnnotationPresent(Subscribe.class)) {
                try {
                    var handle = lookup.unreflect(method);
                    logger.debug("MethodHandle " + handle.getClass().getName() + " unreflected.");
                    handle = handle.bindTo(listener);

                    var priority = method.getAnnotation(Subscribe.class).priority();
                    listeners.put(handle, priority);
                    logger.info("MethodHandle " + method.getName() + " registered");
                } catch (IllegalAccessException e) {
                    logger.error(e.getCause());
                }
            }
        });
    }

    @Override
    public void unregisterListener(Listener listener) {
        var methodsStream = Arrays.stream(listener.getClass().getMethods());

        methodsStream.forEach(method -> {
            if (method.isAnnotationPresent(Subscribe.class)) {
                try {
                    var methodHandle = lookup.unreflect(method).bindTo(listener);
                    listeners.remove(methodHandle);
                    logger.info("MethodHandle " + method.getName() + " unregistered");
                } catch (IllegalAccessException e) {
                    logger.error(e.getCause());
                }
            }
        });
    }

    @Override
    public void broadcast(BaseEvent event) {
        listeners
                .entrySet()
                .stream()
                .filter(methodHandle -> methodHandle.getKey()
                        .type()
                        .parameterList()
                        .stream()
                        .findFirst()
                        .map(eventType -> eventType.equals(event.getClass()))
                        .orElse(false)
                )
                .sorted(Comparator.comparingInt(methodEntry -> methodEntry.getValue().ordinal()))
                .forEach(methodHandle -> {
                    try {
                        methodHandle.getKey().invoke(event);
                        logger.info("Event " + event.getClass().getName() + " broadcast");
                    } catch (Throwable e) {
                        logger.error(e.getCause());
                    }
                });
    }
}
