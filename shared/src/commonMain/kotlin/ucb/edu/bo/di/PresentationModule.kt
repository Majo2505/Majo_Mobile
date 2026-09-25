package ucb.edu.bo.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ucb.edu.bo.signin.presentation.state.SigninViewModel
import ucb.edu.bo.movies.presentation.state.MovieViewModel
import ucb.edu.bo.moviedetail.presentation.state.MovieDetailVM
import ucb.edu.bo.profile.presentation.state.ProfileVM
import ucb.edu.bo.signup.presentation.state.SignUpVM

val presentationModule = module {
    viewModel { SigninViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailVM(get()) }
    viewModel { ProfileVM(get()) }
    viewModel { SignUpVM(get()) }
}
