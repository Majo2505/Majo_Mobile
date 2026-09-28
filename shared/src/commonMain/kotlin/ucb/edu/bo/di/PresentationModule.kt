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

val presentationModule = module {
    viewModel { SigninViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailVM(get()) }
    viewModel { ProfileVM(get()) }
    viewModel { SignUpVM(get()) }
    viewModel { CatalogVM(get()) }
    viewModel { UserInformationViewModel(get()) }
    viewModel { EarthquakeViewModel(get()) }
}
