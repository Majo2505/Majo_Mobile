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

val dataModule = module {
    single<SigninRepository> { SigninRepositoryImpl() }
    single<MovieRepository> { MovieRepositoryImpl() }
    single<MovieDetailRepository> { MovieDetailRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<SignUpRepository> { SignUpRepositoryImpl() }
}
