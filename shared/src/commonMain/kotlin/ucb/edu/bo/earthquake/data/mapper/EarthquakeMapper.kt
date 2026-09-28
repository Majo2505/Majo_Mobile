package ucb.edu.bo.earthquake.data.mapper

import ucb.edu.bo.earthquake.data.dto.FeatureDto
import ucb.edu.bo.earthquake.domain.model.EarthquakeModel

fun FeatureDto.toDomain(): EarthquakeModel {
    return EarthquakeModel(
        place = properties.place ?: "Desconocido",
        magnitude = properties.mag ?: 0.0,
        time = properties.time ?: 0,
        url = properties.url ?: "",
        longitude = geometry.coordinates.getOrNull(0) ?: 0.0,
        latitude = geometry.coordinates.getOrNull(1) ?: 0.0,
        depth = geometry.coordinates.getOrNull(2) ?: 0.0
    )
}
