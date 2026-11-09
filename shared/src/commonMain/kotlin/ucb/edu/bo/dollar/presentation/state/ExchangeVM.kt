package ucb.edu.bo.dollar.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ucb.edu.bo.dollar.domain.usecase.ObserveExchangeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeVM(
    val usecase: ObserveExchangeUseCase
) : ViewModel() {

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    init {
        observeMessage()
    }

    private fun observeMessage() {
        viewModelScope.launch {
            usecase.invoke()
                .collect { message ->
                    _message.value = message ?: "Test"
                }
        }
    }
}