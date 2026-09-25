package ucb.edu.bo.signup.data.repository

import ucb.edu.bo.signup.domain.model.SignUpModel
import ucb.edu.bo.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.delay

class SignUpRepositoryImpl : SignUpRepository {
    override suspend fun register(name: String, email: String, pass: String): SignUpModel {
        delay(500)
        return SignUpModel(id = "1", email = email)
    }
}
