package se2.g12.server.ticket

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import se2.g12.server.service.Service

@Entity
class Ticket(
    @OneToOne
    var service:Service
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var TicketId:Long?=null

}