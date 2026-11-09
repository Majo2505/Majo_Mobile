package ucb.edu.bo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ucb.edu.bo.config.AppDatabase
import ucb.edu.bo.config.getDatabaseBuilder
import ucb.edu.bo.remoteconfig.data.cache.AndroidConfigCache
import ucb.edu.bo.remoteconfig.data.cache.ConfigCache

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        getDatabaseBuilder(get()).build()
    }
    single<ConfigCache> {
        AndroidConfigCache(get())
    }
}