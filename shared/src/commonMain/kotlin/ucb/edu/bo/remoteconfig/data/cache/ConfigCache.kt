package ucb.edu.bo.remoteconfig.data.cache

import ucb.edu.bo.remoteconfig.domain.model.AppConfig

interface ConfigCache {

    fun save(config: AppConfig)

    fun load(): AppConfig?
}