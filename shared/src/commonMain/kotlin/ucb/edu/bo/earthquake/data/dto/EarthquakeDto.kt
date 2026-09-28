package ucb.edu.bo.earthquake.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    val features: List<FeatureDto>
)

@Serializable
data class FeatureDto(
    val properties: PropertiesDto,
    val geometry: GeometryDto
)

@Serializable
data class PropertiesDto(
    val place: String?,
    val mag: Double?,
    val time: Long?,
    val url: String?
)

@Serializable
data class GeometryDto(
    val coordinates: List<Double>
)
