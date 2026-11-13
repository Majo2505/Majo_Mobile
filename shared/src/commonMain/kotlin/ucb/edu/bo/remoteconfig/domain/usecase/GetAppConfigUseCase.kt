package ucb.edu.bo.remoteconfig.domain.usecase

import ucb.edu.bo.remoteconfig.domain.model.AppConfig
import ucb.edu.bo.remoteconfig.domain.repository.AppConfigRepository
import ucb.edu.bo.remoteconfig.domain.repository.AppConfigResult

class GetAppConfigUseCase(
    private val repository: AppConfigRepository
) {

    suspend operator fun invoke(): AppConfig {
        return repository
            .getConfig()
            .getOrDefault(AppConfig.DEFAULT)
    }

    suspend fun getWithCache(): AppConfigResult {
        return repository.getConfigWithCache()
    }
}