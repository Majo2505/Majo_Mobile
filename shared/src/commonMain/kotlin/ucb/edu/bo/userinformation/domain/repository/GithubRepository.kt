package ucb.edu.bo.userinformation.domain.repository

import ucb.edu.bo.userinformation.domain.model.UserInfoModel

interface GithubRepository {
    suspend fun findByAlias(alias: String): Result<UserInfoModel>
}
