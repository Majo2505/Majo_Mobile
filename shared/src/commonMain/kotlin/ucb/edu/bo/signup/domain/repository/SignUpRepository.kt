package ucb.edu.bo.signup.domain.repository

import ucb.edu.bo.signup.domain.model.SignUpModel

interface SignUpRepository {
    suspend fun register(name: String, email: String, pass: String): SignUpModel
}
