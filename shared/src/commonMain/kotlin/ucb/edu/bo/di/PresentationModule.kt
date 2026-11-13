package ucb.edu.bo.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ucb.edu.bo.signin.presentation.state.SigninViewModel
import ucb.edu.bo.movies.presentation.state.MovieViewModel
import ucb.edu.bo.moviedetail.presentation.state.MovieDetailVM
import ucb.edu.bo.profile.presentation.state.ProfileVM
import ucb.edu.bo.signup.presentation.state.SignUpVM
import ucb.edu.bo.catalog.presentation.state.CatalogVM
import ucb.edu.bo.earthquake.presentation.states.EarthquakeViewModel
import ucb.edu.bo.userinformation.presentation.states.UserInformationViewModel
import ucb.edu.bo.dollar.presentation.state.DollarVM
import ucb.edu.bo.dollar.presentation.state.ExchangeVM
import ucb.edu.bo.remoteconfig.presentation.state.ConfigVM

val presentationModule = module {
    viewModel { SigninViewModel(get(), get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailVM(get()) }
    viewModel { ProfileVM(get(), get()) }
    viewModel { SignUpVM(get()) }
    viewModel { CatalogVM(get()) }
    viewModel { UserInformationViewModel(get()) }
    viewModel { EarthquakeViewModel(get()) }
    viewModel { DollarVM(get(), get(), get(), get()) }
    viewModel { ExchangeVM(get()) }
    viewModel { ConfigVM(get()) }
}
