package it.polito.se2.g12.officequeuemanagement.user

import it.polito.se2.g12.officequeuemanagement.ticket.TicketDTO
import it.polito.se2.g12.officequeuemanagement.user.UserDTO
import it.polito.se2.g12.officequeuemanagement.user.User
import java.util.UUID

interface UserService {
    fun createUser(username: String, fullName: String, email: String): UserDTO
    fun getUserById(userId: Long): UserDTO?
    fun updateUser(userId: Long, updatedUser: UserDTO): UserDTO?
    fun deleteUser(userId: Long): Boolean
    fun findByUsername(username: String): User?

    //fun assignTicket(userId: Long, ticketId: Long ): UserDTO

    // other required user methods
}