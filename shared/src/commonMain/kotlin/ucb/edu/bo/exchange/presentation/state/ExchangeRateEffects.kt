package ucb.edu.bo.exchange.presentation.state

sealed interface ExchangeRateEffects {
    data class ShowToast(val message: String) : ExchangeRateEffects
    object NavigateBack : ExchangeRateEffects
}
