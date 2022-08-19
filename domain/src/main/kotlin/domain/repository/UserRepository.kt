package domain.repository

import domain.entity.Email
import domain.entity.user.User

interface UserRepository : Repository<User, Int> {
    suspend fun findByEmail(email: Email): User?
}
