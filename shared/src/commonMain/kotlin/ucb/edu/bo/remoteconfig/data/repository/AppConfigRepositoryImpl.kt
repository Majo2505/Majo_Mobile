package ucb.edu.bo.remoteconfig.data.repository

import ucb.edu.bo.remoteconfig.data.cache.ConfigCache
import ucb.edu.bo.remoteconfig.data.datasource.RemoteConfigDataSource
import ucb.edu.bo.remoteconfig.domain.model.AppConfig
import ucb.edu.bo.remoteconfig.domain.repository.AppConfigRepository
import ucb.edu.bo.remoteconfig.domain.repository.AppConfigResult

class AppConfigRepositoryImpl(
    private val dataSource: RemoteConfigDataSource,
    private val cache: ConfigCache
) : AppConfigRepository {

    override suspend fun getConfig(): Result<AppConfig> = runCatching {
        dataSource.fetch()
    }

    override suspend fun getConfigWithCache(): AppConfigResult {

        return try {
            val remoteConfig = dataSource.fetch()

            cache.save(remoteConfig)

            AppConfigResult(
                config = remoteConfig,
                isOffline = false
            )
        } catch (e: Exception) {

            val cachedConfig = cache.load() ?: AppConfig.DEFAULT

            AppConfigResult(
                config = cachedConfig,
                isOffline = true
            )
        }
    }
}