package ir.hasanazimi.android_lab.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasanazimi.android_lab.domain.XUseCase
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val xUseCase: XUseCase
) : ViewModel() {

}