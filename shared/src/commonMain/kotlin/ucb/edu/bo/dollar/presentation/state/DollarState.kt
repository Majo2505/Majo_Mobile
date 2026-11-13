package ucb.edu.bo.dollar.presentation.state

import ucb.edu.bo.dollar.domain.model.DollarModel

data class DollarState(
    val officialInput: String = "",
    val parallelInput: String = "",
    val list: List<DollarModel> = emptyList(),
    val realtimeValue: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)