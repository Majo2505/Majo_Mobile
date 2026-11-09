package ucb.edu.bo.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ucb.edu.bo.config.AppDatabase
import ucb.edu.bo.config.getDatabaseBuilder

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        getDatabaseBuilder(get()).build()
    }
}