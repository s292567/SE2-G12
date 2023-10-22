package it.polito.se2.g12.officequeuemanagement.user

import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun createUser(username: String, fullName: String, email: String): UserDTO {
        val user = User(username = username, fullName = fullName, email = email)
        val savedUser = userRepository.save(user)
        return UserDTO(savedUser.userId, savedUser.username, savedUser.fullName, savedUser.email, savedUser.tickets)
    }

    override fun getUserById(userId: Long): UserDTO? {
        val user = userRepository.findById(userId)
        return user.map { UserDTO(it.userId, it.username, it.fullName, it.email, it.tickets) }.orElse(null)
    }

    override fun updateUser(userId: Long, updatedUser: UserDTO): UserDTO? {
        val existingUser = userRepository.findById(userId)
        if (existingUser.isPresent) {
            val user = existingUser.get()
            user.username = updatedUser.username
            user.fullName = updatedUser.fullName
            user.email = updatedUser.email
            val savedUser = userRepository.save(user)
            return UserDTO(savedUser.userId, savedUser.username, savedUser.fullName, savedUser.email, savedUser.tickets)
        }
        return null
    }

    override fun deleteUser(userId: Long): Boolean {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            return true
        }
        return false
    }
    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    //override fun assignTicket(userId: Long, ticketId: Long): UserDTO {
    // Step 1: Retrieve the user based on userId
    // Step 2: Retrieve the ticket based on ticketId
    // Step 3: Update the user's list of tickets
    // Step 4: Save the updated user
    // Convert the updated user to UserDTO
    //}

}
