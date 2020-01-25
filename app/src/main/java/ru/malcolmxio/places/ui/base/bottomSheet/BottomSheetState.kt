package ru.malcolmxio.places.ui.base.bottomSheet

sealed class BottomSheetState {

    object StateNotShown: BottomSheetState()

    object StateHalfExpanded: BottomSheetState()

    object StateFullyExpanded: BottomSheetState()

}