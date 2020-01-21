package ru.malcolmxio.places.presentation.flow

import ru.malcolmxio.places.ui.base.BaseFragment

/**
 * Interface implemented by flow fragments.
 */
interface Navigator {

    fun moveTo(screen: BaseFragment)

    fun handleBackButtonEvent()

}