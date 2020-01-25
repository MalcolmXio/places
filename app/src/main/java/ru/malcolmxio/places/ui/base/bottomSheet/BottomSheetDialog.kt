package ru.malcolmxio.places.ui.base.bottomSheet

import android.view.View

/**
 * @property show - shows BottomSheet first time at not fully-expanded state.
 * @property expand - expands BottomSheet to target height.
 * @property collapse - collapses BottomSheet to start state called in show().
 * @property hide - fully collapses BottomSheet.
 * @property onHeaderClicked - performs click on BottomSheet header which should update BottomSheet state.
 * @property onElementClicked - performs click on BottomSheet child element (e.g.: click on "Call" button).
 */
interface BottomSheetDialog {

    fun show()

    fun expand()

    fun collapse()

    fun hide()

    fun onHeaderClicked()

    fun onElementClicked(element: View)

}