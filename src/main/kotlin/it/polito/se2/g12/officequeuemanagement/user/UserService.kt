package it.polito.se2.g12.officequeuemanagement.user

interface UserService {
    fun createUser(username: String, fullName: String, email: String): UserDTO
    fun getUserById(userId: Long): UserDTO?
    fun updateUser(userId: Long, updatedUser: UserDTO): UserDTO?
    fun deleteUser(userId: Long): Boolean
    fun findByUsername(username: String): User?

    //fun assignTicket(userId: Long, ticketId: Long ): UserDTO

    // other required user methods
}