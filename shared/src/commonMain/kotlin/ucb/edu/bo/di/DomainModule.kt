import org.koin.dsl.module
import ucb.edu.bo.signin.domain.usecase.SigninUseCase

val domainModule = module {
    factory { SigninUseCase(get()) }
}
