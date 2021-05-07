package com.erikschouten.cleanarchitecture.usecases.user

import com.erikschouten.cleanarchitecture.LoginException
import com.benasher44.uuid.Uuid
import com.erikschouten.cleanarchitecture.models.UserModel
import com.erikschouten.cleanarchitecture.repositories.UserRepository
import com.erikschouten.cleanarchitecture.usecases.Usecase
import com.erikschouten.cleanarchitecture.usecases.UsecaseA1

@Usecase
class AuthenticateUser(
    private val repository: UserRepository,
) : UsecaseA1<Uuid, UserModel>(Uuid::class, UserModel::class) {

    override val executor = { _: UserModel?, a0: Uuid ->
        repository.findById(a0)?.let { UserModel.of(it) } ?: throw LoginException()
    }
}
