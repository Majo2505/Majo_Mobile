package ucb.edu.bo.di

import org.koin.dsl.module
import ucb.edu.bo.signin.domain.usecase.SigninUseCase
import ucb.edu.bo.movies.domain.usecase.GetMoviesUseCase
import ucb.edu.bo.moviedetail.domain.usecase.GetMovieDetailUseCase
import ucb.edu.bo.profile.domain.usecase.GetProfileUseCase
import ucb.edu.bo.signup.domain.usecase.RegisterUseCase

val domainModule = module {
    factory { SigninUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
    factory { GetProfileUseCase(get()) }
    factory { RegisterUseCase(get()) }
}
