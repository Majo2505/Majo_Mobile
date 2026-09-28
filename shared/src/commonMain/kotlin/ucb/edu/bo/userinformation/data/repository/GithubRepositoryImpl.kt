package ucb.edu.bo.userinformation.data.repository

import ucb.edu.bo.userinformation.data.datasource.GithubRemoteDataSource
import ucb.edu.bo.userinformation.data.mapper.toDomain
import ucb.edu.bo.userinformation.domain.model.UserInfoModel
import ucb.edu.bo.userinformation.domain.repository.GithubRepository

class GithubRepositoryImpl(val dataSource: GithubRemoteDataSource) : GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> {
        return try {
            Result.success(dataSource.getUser(alias).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
