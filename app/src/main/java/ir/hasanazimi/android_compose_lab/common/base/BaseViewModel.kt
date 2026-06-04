package ir.hasanazimi.android_compose_lab.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, Event, SideEffect>(initialState: UiState) : ViewModel() {
    private val TAG = this::class.java.simpleName
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    abstract fun onEvent(event: Event)

    protected fun updateState(reduce: (UiState) -> UiState) {
        Log.i(TAG, "updateState: $reduce")
        _uiState.update { currentState ->
            reduce(currentState)
        }
    }

    protected fun sendEffect(vararg effect: SideEffect) {
        Log.i(TAG, "sendEffect: $effect")
        effect.forEach {
            viewModelScope.launch { _sideEffect.send(it) }
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared: ")
        super.onCleared()
    }
}