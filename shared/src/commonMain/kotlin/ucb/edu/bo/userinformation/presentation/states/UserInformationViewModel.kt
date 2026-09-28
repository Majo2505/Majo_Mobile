package ucb.edu.bo.userinformation.presentation.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ucb.edu.bo.userinformation.domain.usecase.FindAliasUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInformationViewModel(
    val findAliasUseCase: FindAliasUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserInformationState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserInformationEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: UserInformationEvent) {
        when (event) {
            is UserInformationEvent.OnAliasChange -> {
                _state.update { it.copy(alias = event.value) }
            }
            UserInformationEvent.OnBack -> {
                emitEffect(UserInformationEffect.NavigateToBack)
            }
            UserInformationEvent.OnSubmit -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true, error = null) }
                    findAliasUseCase.invoke(_state.value.alias).fold(
                        onSuccess = { userInfo ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    email = userInfo.email,
                                    company = userInfo.company,
                                    avatarUrl = userInfo.avatarUrl
                                )
                            }
                        },
                        onFailure = {
                            _state.update { it.copy(isLoading = false) }
                            emitEffect(UserInformationEffect.ShowToast(it.message ?: "Error"))
                        }
                    )
                }
            }
        }
    }

    private fun emitEffect(effect: UserInformationEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
