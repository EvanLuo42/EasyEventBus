package com.phakel.event;

import java.lang.annotation.*;

/**
 * An annotation for signing a Listener
 * @author EvanLuo42
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    /**
     * @return The event this Listener subscribed.
     */
    Class<? extends BaseEvent> event();

    /**
     * @return The priority of this Listener.
     */
    Priority priority() default Priority.NORMAL;
}
