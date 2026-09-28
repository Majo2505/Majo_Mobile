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

}