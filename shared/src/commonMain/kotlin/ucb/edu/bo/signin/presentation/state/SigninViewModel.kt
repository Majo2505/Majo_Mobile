package ucb.edu.bo.signin.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ucb.edu.bo.signin.domain.usecase.SigninUseCase
import ucb.edu.bo.signin.domain.vo.Email
import ucb.edu.bo.signin.domain.vo.Password
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SigninViewModel(private val signinUseCase: SigninUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(SigninState())
    val uiState: StateFlow<SigninState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<SigninEffects>()
    val uiEffect: SharedFlow<SigninEffects> = _uiEffect.asSharedFlow()

    fun onEvent(event: SigninEvents) {
        when (event) {
            is SigninEvents.OnEmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
            }
            is SigninEvents.OnPasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
            }
            is SigninEvents.OnLoginClicked -> {
                login()
            }
            is SigninEvents.OnSignUpClicked -> {
                viewModelScope.launch {
                    _uiEffect.emit(SigninEffects.NavigateToSignUp)
                }
            }
        }
    }

    private fun login() {
        val currentState = _uiState.value
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _uiState.update { it.copy(error = "Completa todos los campos") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                signinUseCase.invoke(
                    email = Email(currentState.email),
                    password = Password(currentState.password)
                )
                _uiState.update { it.copy(isLoading = false) }
                _uiEffect.emit(SigninEffects.NavigateToHome)
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Authentication failed") }
            }
        }
    }
}
