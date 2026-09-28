package ucb.edu.bo.earthquake.domain.usecase

import ucb.edu.bo.earthquake.domain.model.EarthquakeModel
import ucb.edu.bo.earthquake.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(
    private val repository: EarthquakeRepository
) {
    suspend fun invoke(): Result<List<EarthquakeModel>> {
        return repository.getEarthquakes()
    }
}
