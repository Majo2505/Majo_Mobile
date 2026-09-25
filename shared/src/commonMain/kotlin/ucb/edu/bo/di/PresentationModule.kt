import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ucb.edu.bo.signin.presentation.state.SigninViewModel

val presentationModule = module {
    viewModel { SigninViewModel(get()) }
}
