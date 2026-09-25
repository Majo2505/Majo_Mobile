package ucb.edu.bo.signin.data.repository

import ucb.edu.bo.signin.domain.model.UserModel
import ucb.edu.bo.signin.domain.repository.SigninRepository
import ucb.edu.bo.signin.domain.vo.Email
import ucb.edu.bo.signin.domain.vo.Password

class SigninRepositoryImpl : SigninRepository {
    override suspend fun login(email: Email, password: Password): Result<UserModel> {
        // Datos hardcodeados para probar el flujo por ahora
        return if (email.value == "test@ucb.edu.bo" && password.value == "123456") {
            Result.success(
                UserModel(
                    id = "1",
                    email = email.value,
                    token = "fake-token-123"
                )
            )
        } else {
            Result.failure(Exception("Credenciales incorrectas"))
        }
    }
}