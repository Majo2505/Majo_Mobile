package ucb.edu.bo.signup.domain.usecase

import ucb.edu.bo.signup.domain.model.SignUpModel
import ucb.edu.bo.signup.domain.repository.SignUpRepository

class RegisterUseCase(private val repository: SignUpRepository) {
    suspend operator fun invoke(name: String, email: String, pass: String): SignUpModel {
        return repository.register(name, email, pass)
    }
}
