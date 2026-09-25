package ucb.edu.bo.signin.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ucb.edu.bo.signin.domain.usecase.SigninUseCase
import ucb.edu.bo.signin.domain.vo.Email
import ucb.edu.bo.signin.domain.vo.Password

class SigninViewModel(
    private val signinUseCase: SigninUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SigninStates())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<SigninEffects>()
    val effects = _effects.asSharedFlow()

    private fun emitEffect(effect: SigninEffects) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    fun emitEvent(event: SigninEvents) {
        when (event) {
            is SigninEvents.OnEmailChanged -> {
                _state.value = _state.value.copy(userName = event.value)
            }
            is SigninEvents.OnPasswordChanged -> {
                _state.value = _state.value.copy(password = event.value)
            }
            SigninEvents.OnSubmit -> {
                val currentState = _state.value
                var isValid = true

                if (currentState.userName.isBlank()) {
                    emitEffect(SigninEffects.ShowToast("The email field is required"))
                    isValid = false
                } else if (currentState.password.isBlank()) {
                    emitEffect(SigninEffects.ShowToast("The password field is required"))
                    isValid = false
                }

                if (isValid) {
                    viewModelScope.launch {
                        try {
                            _state.value = _state.value.copy(isLoading = true)
                            // Invocamos al caso de uso de dominio
                            val result = signinUseCase.invoke(
                                email = Email(currentState.userName),
                                password = Password(currentState.password)
                            )
                            _state.value = _state.value.copy(isLoading = false)
                            // Si todo sale bien, lanzamos el efecto de éxito/navegación
                            emitEffect(SigninEffects.NavigateToHome)
                        } catch (e: Exception) {
                            _state.value = _state.value.copy(isLoading = false)
                            emitEffect(SigninEffects.ShowError(e.message ?: "Authentication failed"))
                        }
                    }
                }
            }
            SigninEvents.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }
        }
    }
}