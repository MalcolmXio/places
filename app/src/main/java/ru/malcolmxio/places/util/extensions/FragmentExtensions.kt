package ru.malcolmxio.places.util.extensions

import ru.malcolmxio.places.App
import ru.malcolmxio.places.ui.RouteActivity
import ru.malcolmxio.places.ui.base.BaseFragment

fun BaseFragment.getApplication() =
    (this.requireActivity() as RouteActivity).application as App