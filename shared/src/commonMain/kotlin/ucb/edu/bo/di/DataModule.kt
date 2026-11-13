package ucb.edu.bo.di

import org.koin.dsl.module
import ucb.edu.bo.signin.data.repository.SigninRepositoryImpl
import ucb.edu.bo.signin.domain.repository.SigninRepository
import ucb.edu.bo.movies.data.repository.MovieRepositoryImpl
import ucb.edu.bo.movies.domain.repository.MovieRepository
import ucb.edu.bo.moviedetail.data.repository.MovieDetailRepositoryImpl
import ucb.edu.bo.moviedetail.domain.repository.MovieDetailRepository
import ucb.edu.bo.profile.data.repository.ProfileRepositoryImpl
import ucb.edu.bo.profile.domain.repository.ProfileRepository
import ucb.edu.bo.signup.data.repository.SignUpRepositoryImpl
import ucb.edu.bo.signup.domain.repository.SignUpRepository
import ucb.edu.bo.catalog.data.datasource.CatalogRemoteDataSource
import ucb.edu.bo.catalog.data.repository.CatalogRepositoryImpl
import ucb.edu.bo.catalog.data.service.CatalogApiService
import ucb.edu.bo.catalog.domain.repository.CatalogRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ucb.edu.bo.earthquake.data.datasource.EarthquakeRemoteDataSource
import ucb.edu.bo.earthquake.data.repository.EarthquakeRepositoryImpl
import ucb.edu.bo.earthquake.data.service.EarthquakeApiService
import ucb.edu.bo.earthquake.domain.repository.EarthquakeRepository
import ucb.edu.bo.userinformation.data.datasource.GithubRemoteDataSource
import ucb.edu.bo.userinformation.data.repository.GithubRepositoryImpl
import ucb.edu.bo.userinformation.data.service.GitHubApiService
import ucb.edu.bo.userinformation.domain.repository.GithubRepository
import ucb.edu.bo.config.AppDatabase
import ucb.edu.bo.dollar.data.dao.DollarDao
import ucb.edu.bo.dollar.data.datasource.DollarLocalDataSource
import ucb.edu.bo.dollar.data.datasource.RealTimeDataBase
import ucb.edu.bo.dollar.data.repository.DollarRepositoryImpl
import ucb.edu.bo.dollar.domain.repository.DollarRepository
import org.koin.core.module.dsl.singleOf
import ucb.edu.bo.analytics.AnalyticsTracker
import ucb.edu.bo.core.grpc.GrpcConfig
import ucb.edu.bo.dollar.data.datasource.DollarFirestoreDataSource
import ucb.edu.bo.dollar.data.repository.ExchangeRepositoryImpl
import ucb.edu.bo.dollar.domain.repository.ExchangeRepository
import ucb.edu.bo.logs.data.datasource.LogsRemoteDataSource
import ucb.edu.bo.logs.data.repository.LogsRepositoryImpl
import ucb.edu.bo.logs.data.service.LogsService
import ucb.edu.bo.logs.domain.repository.LogsRepository
import ucb.edu.bo.push.data.datasource.PushTokenDataBase
import ucb.edu.bo.push.data.datasource.PushTokenFirestoreDataSource
import ucb.edu.bo.push.data.provider.PushTokenProvider
import ucb.edu.bo.push.data.repository.PushTokenRepositoryImpl
import ucb.edu.bo.push.domain.repository.PushTokenRepository
import ucb.edu.bo.remoteconfig.data.datasource.RemoteConfigDataSource
import ucb.edu.bo.remoteconfig.data.repository.AppConfigRepositoryImpl
import ucb.edu.bo.remoteconfig.domain.repository.AppConfigRepository

val dataModule = module {

    single<MovieRepository> { MovieRepositoryImpl() }
    single<SigninRepository> { SigninRepositoryImpl() }
    single<SignUpRepository> { SignUpRepositoryImpl() }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
    single<CatalogRemoteDataSource> { CatalogApiService() }
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }
    single<EarthquakeRemoteDataSource> { EarthquakeApiService() }
    single<EarthquakeRepository> { EarthquakeRepositoryImpl(get()) }
    single<DollarDao> { get<AppDatabase>().getDao() }
    singleOf(::DollarLocalDataSource)
    single { RealTimeDataBase() }
    singleOf(::DollarFirestoreDataSource)
    single<DollarRepository> { DollarRepositoryImpl(get(), get(), get()) }
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }

    // Notificaciones push: token FCM en Realtime Database y Firestore
    singleOf(::PushTokenProvider)
    singleOf(::PushTokenDataBase)
    singleOf(::PushTokenFirestoreDataSource)
    single<PushTokenRepository> { PushTokenRepositoryImpl(get(), get(), get()) }

    // Remote Config con cache (la cache viene del platformModule)
    singleOf(::RemoteConfigDataSource)
    single<AppConfigRepository> { AppConfigRepositoryImpl(dataSource = get(), cache = get()) }

    // Analytics y logs por gRPC
    singleOf(::AnalyticsTracker)
    single<LogsRemoteDataSource> { LogsService(GrpcConfig.LOGS_URL) }
    single<LogsRepository> { LogsRepositoryImpl(get()) }

}