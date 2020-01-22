package ru.malcolmxio.places.presentation.flow

import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Interface implemented by flow fragments.
 */
abstract class Navigator(private val controller: NavController) {

    open fun moveTo(directions: NavDirections) =
        controller.navigate(directions)

    open fun moveTo(destinationRes: Int) =
        controller.navigate(destinationRes)

    open fun navigateBack() =
        controller.popBackStack()

    abstract fun handleBackButtonEvent()

}