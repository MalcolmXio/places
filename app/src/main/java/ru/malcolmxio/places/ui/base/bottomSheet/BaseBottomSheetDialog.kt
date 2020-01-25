package ru.malcolmxio.places.ui.base.bottomSheet

import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import ru.malcolmxio.places.util.extensions.doOnApplyWindowInsets

class BaseBottomSheetDialog(
    private val container: ViewGroup
) : BottomSheetDialog {

    private val bottomSheetBehaviour: BottomSheetBehavior<View> =
        BottomSheetBehavior.from(container)
    private val headerView = container.headerContainer
    private var bottomInsetPadding = 0

    init {
        container.doOnApplyWindowInsets { _, insets, _ ->
            bottomInsetPadding = insets.systemWindowInsetBottom
            show()
            insets
        }
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehaviour.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) = Unit

            override fun onStateChanged(view: View, state: Int) {
                when (state) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        //show()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    else -> {
                    }
                }
            }
        })
        headerView.setOnClickListener {
            onHeaderClicked()
        }
    }

    override fun show() {
        bottomSheetBehaviour.peekHeight =
            headerView.measuredHeight + bottomInsetPadding
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expand() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun collapse() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun hide() {
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onHeaderClicked() {
        when (bottomSheetBehaviour.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                collapse()
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                expand()
            }
            else -> Unit
        }
    }

    override fun onElementClicked(element: View) {
    }

}