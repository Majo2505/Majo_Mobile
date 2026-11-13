package ucb.edu.bo.di

import org.koin.dsl.module
import ucb.edu.bo.signin.domain.usecase.SigninUseCase
import ucb.edu.bo.movies.domain.usecase.GetMoviesUseCase
import ucb.edu.bo.moviedetail.domain.usecase.GetMovieDetailUseCase
import ucb.edu.bo.profile.domain.usecase.GetProfileUseCase
import ucb.edu.bo.signup.domain.usecase.RegisterUseCase
import ucb.edu.bo.catalog.domain.usecase.GetCatalogMoviesUseCase
import ucb.edu.bo.earthquake.domain.usecase.GetEarthquakesUseCase
import ucb.edu.bo.userinformation.domain.usecase.FindAliasUseCase
import ucb.edu.bo.dollar.domain.usecase.GetDollarListUseCase
import ucb.edu.bo.dollar.domain.usecase.InsertDollarUseCase
import ucb.edu.bo.dollar.domain.usecase.ObserveDollarUseCase
import ucb.edu.bo.dollar.domain.usecase.ObserveExchangeUseCase
import ucb.edu.bo.dollar.domain.usecase.SyncDollarUseCase
import ucb.edu.bo.logs.domain.usecase.SendLogsUseCase
import ucb.edu.bo.push.domain.usecase.RefreshPushTokenUseCase
import ucb.edu.bo.push.domain.usecase.RegisterPushTokenUseCase
import ucb.edu.bo.push.domain.usecase.UnregisterPushTokenUseCase
import ucb.edu.bo.remoteconfig.domain.usecase.GetAppConfigUseCase

val domainModule = module {
    factory { SigninUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
    factory { GetProfileUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetCatalogMoviesUseCase(get()) }
    factory { FindAliasUseCase(get()) }
    factory { GetEarthquakesUseCase(get()) }
    factory { GetDollarListUseCase(get()) }
    factory { InsertDollarUseCase(get()) }
    factory { ObserveDollarUseCase(get()) }
    factory { ObserveExchangeUseCase(get()) }
    factory { SyncDollarUseCase(get()) }
    factory { RegisterPushTokenUseCase(get()) }
    factory { UnregisterPushTokenUseCase(get()) }
    factory { RefreshPushTokenUseCase(get()) }
    factory { GetAppConfigUseCase(get()) }
    factory { SendLogsUseCase(get()) }
}
