package ucb.edu.bo.earthquake.presentation.states

import ucb.edu.bo.earthquake.domain.model.EarthquakeModel

data class EarthquakeState(
    val isLoading: Boolean = false,
    val earthquakes: List<EarthquakeModel> = emptyList(),
    val error: String? = null
)
