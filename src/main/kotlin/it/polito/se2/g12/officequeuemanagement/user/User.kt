package it.polito.se2.g12.officequeuemanagement.user;

import it.polito.se2.g12.officequeuemanagement.service.Service
import it.polito.se2.g12.officequeuemanagement.ticket.Ticket
import jakarta.persistence.*

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val userId: Long? = null,
        var username: String,
        var fullName: String,
        var email: String,

        @OneToMany(mappedBy = "user")
        var tickets: List<Ticket>? = mutableListOf()
)