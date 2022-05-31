package usecases.model

import domain.entity.user.*
import domain.repository.PaginationResult
import usecases.dependency.PasswordEncoder

data class UserModel(
    val id: Int,
    val email: Email,
    val authorities: List<Authorities>,
    val locked: Boolean
) {
    constructor(user: User) : this(user.id, user.email, user.authorities, user.locked)
}

data class UpdateUserModel(
    val id: Int,
    val email: Email,
    val authorities: List<Authorities>,
    val locked: Boolean
) {
    fun toUser(hash: PasswordHash) =
        User(id = id, email = email, authorities = authorities, password = hash, locked = locked)
}

data class ChangeOwnPasswordModel(
    val current: Password,
    val password: NewPassword
) {
    override fun toString(): String {
        return "ChangeOwnPasswordModel()"
    }
}

data class ChangePasswordModel(
    val id: Int,
    val password: NewPassword
) {
    override fun toString(): String {
        return "ChangePasswordModel(id=$id)"
    }
}

data class CreateUserModel(
    val email: Email,
    val authorities: List<Authorities>,
    val password: NewPassword,
    val locked: Boolean
) {
    fun toUser(encoder: PasswordEncoder) =
        User(email = email, authorities = authorities, password = encoder.encode(password), locked = locked)

    override fun toString(): String {
        return "CreateUserModel(email=$email, authorities=$authorities, locked=$locked)"
    }
}

data class LoginUserModel(
    val email: Email,
    val password: Password
) {
    override fun toString(): String {
        return "LoginUserModel(email=$email)"
    }
}

class UserPaginationResult(pagination: PaginationResult<User>) :
    PaginationResult<UserModel>(pagination.transform { UserModel(it) })
