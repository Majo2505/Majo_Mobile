package ucb.edu.bo.signin.domain.usecase

import ucb.edu.bo.signin.domain.model.UserModel
import ucb.edu.bo.signin.domain.repository.SigninRepository
import ucb.edu.bo.signin.domain.vo.Email
import ucb.edu.bo.signin.domain.vo.Password

class SigninUseCase(private val repository: SigninRepository) {
    suspend operator fun invoke(email: Email, password: Password): Result<UserModel> {
        if (!email.isValid()) return Result.failure(IllegalArgumentException("Correo inválido"))
        if (!password.isValid()) {
            return Result.failure(IllegalArgumentException("La contraseña debe tener al menos 6 caracteres"))
        }
        return repository.login(email, password)
    }
}
