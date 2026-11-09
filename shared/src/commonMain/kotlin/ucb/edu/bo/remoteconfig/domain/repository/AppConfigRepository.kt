package ucb.edu.bo.remoteconfig.domain.repository

import ucb.edu.bo.remoteconfig.domain.model.AppConfig

data class AppConfigResult(
    val config: AppConfig,
    val isOffline: Boolean
)

interface AppConfigRepository {

    suspend fun getConfig(): Result<AppConfig>

    suspend fun getConfigWithCache(): AppConfigResult
}