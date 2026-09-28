package ucb.edu.bo.userinformation.presentation.states

sealed interface UserInformationEffect {
    data class ShowToast(val message: String) : UserInformationEffect
    object NavigateToBack : UserInformationEffect
    object NavigateToHome : UserInformationEffect
}
