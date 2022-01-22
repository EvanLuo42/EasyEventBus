package cn.phakel.easyeventbus.listener

import cn.phakel.easyeventbus.event.Event
import kotlin.reflect.KClass

/**
 * Annotation Class for @Subscribe
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: KClass<out Event>, val priority: ListenerPriority)