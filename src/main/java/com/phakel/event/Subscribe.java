package com.phakel.event;

import java.lang.annotation.*;

/**
 * An annotation for signing a Listener
 * @author EvanLuo42
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    /**
     * @return The priority of this Listener.
     */
    Priority priority() default Priority.NORMAL;
}
