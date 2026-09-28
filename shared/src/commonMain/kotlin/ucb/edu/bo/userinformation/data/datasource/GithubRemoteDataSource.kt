package ucb.edu.bo.userinformation.data.datasource

import ucb.edu.bo.userinformation.data.dto.UserInfoDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname: String): UserInfoDto
}
