package ucb.edu.bo.exchange.presentation.state

import ucb.edu.bo.exchange.domain.model.ExchangeRateModel

data class ExchangeRateState(
    val isLoading: Boolean = false,
    val rates: List<ExchangeRateModel> = emptyList(),
    val errorMessage: String? = null
)
