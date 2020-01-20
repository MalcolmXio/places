package ru.malcolmxio.places.util.extensions

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"