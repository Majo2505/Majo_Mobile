package ucb.edu.bo.userinformation.data.mapper

import ucb.edu.bo.userinformation.data.dto.UserInfoDto
import ucb.edu.bo.userinformation.domain.model.UserInfoModel

fun UserInfoDto.toDomain(): UserInfoModel = UserInfoModel(
    email = email ?: "",
    company = company ?: "",
    avatarUrl = avatarUrl ?: "",
    alias = login ?: ""
)
