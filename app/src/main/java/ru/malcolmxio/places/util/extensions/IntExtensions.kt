package ru.malcolmxio.places.util.extensions

import android.content.res.Resources

fun Int.px() = (this * Resources.getSystem().displayMetrics.density).toInt()