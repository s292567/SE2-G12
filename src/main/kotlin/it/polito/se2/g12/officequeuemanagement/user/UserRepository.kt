package it.polito.se2.g12.officequeuemanagement.user

import it.polito.se2.g12.officequeuemanagement.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}