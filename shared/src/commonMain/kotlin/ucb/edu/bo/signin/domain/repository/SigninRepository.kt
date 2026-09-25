package ucb.edu.bo.signin.domain.repository

import ucb.edu.bo.signin.domain.model.UserModel
import ucb.edu.bo.signin.domain.vo.Email
import ucb.edu.bo.signin.domain.vo.Password

interface SigninRepository {
    suspend fun login(email: Email, password: Password): Result<UserModel>
}
