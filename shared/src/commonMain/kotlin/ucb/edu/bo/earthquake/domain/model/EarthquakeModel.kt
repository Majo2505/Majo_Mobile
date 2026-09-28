package ucb.edu.bo.earthquake.domain.model

data class EarthquakeModel(
    val place: String,
    val magnitude: Double,
    val time: Long,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val depth: Double
)
