package it.polito.se2.g12.officequeuemanagement.user;

import it.polito.se2.g12.officequeuemanagement.ticket.Ticket
import java.util.UUID

data class UserDTO(
        val userId: Long?,
        val username: String,
        val fullName: String,
        val email: String,
        val tickets: List<Ticket>?
)
