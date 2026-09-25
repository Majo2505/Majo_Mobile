import org.koin.dsl.module
import ucb.edu.bo.signin.data.repository.SigninRepositoryImpl
import ucb.edu.bo.signin.domain.repository.SigninRepository

val dataModule = module {
    single<SigninRepository> { SigninRepositoryImpl() }
}