package ucb.edu.bo.exchange.presentation.state

sealed interface ExchangeRateEvents {
    object LoadRates : ExchangeRateEvents
    data class SaveRate(val official: String, val parallel: String) : ExchangeRateEvents
    data class SelectRate(val id: Int) : ExchangeRateEvents
}
