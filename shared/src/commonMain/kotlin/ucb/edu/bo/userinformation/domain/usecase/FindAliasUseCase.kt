package ucb.edu.bo.userinformation.domain.usecase

import ucb.edu.bo.userinformation.domain.model.UserInfoModel
import ucb.edu.bo.userinformation.domain.repository.GithubRepository

class FindAliasUseCase(
    val repository: GithubRepository
) {
    suspend fun invoke(alias: String): Result<UserInfoModel> {
        return repository.findByAlias(alias)
    }
}
