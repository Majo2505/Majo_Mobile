package ucb.edu.bo.userinformation.presentation.states

sealed interface UserInformationEvent {
    object OnBack : UserInformationEvent
    object OnSubmit : UserInformationEvent
    data class OnAliasChange(val value: String) : UserInformationEvent
}
