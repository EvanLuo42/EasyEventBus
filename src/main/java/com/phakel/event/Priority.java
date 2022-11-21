package com.phakel.event;

/**
 * An enum listed all supported priorities.
 * @author EvanLuo42
 */
public enum Priority {
    /**
     * Event is listened to purely for monitoring
     * the outcome of an event.
     */
    MONITOR,

    /**
     * Event call is critical and must have the final say in what
     * happens to the event.
     */
    HIGHEST,

    /**
     * Event call is of high importance.
     */
    HIGH,

    /**
     * Event call is neither important nor unimportant,
     * and may be run normally.
     */
    NORMAL,

    /**
     * Event call is of low importance.
     */
    LOW,

    /**
     * Event call is of very low importance and should be run first,
     * to allow other plugins to further customise the outcome.
     */
    LOWEST
}
