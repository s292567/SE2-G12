package it.polito.se2.g12.officequeuemanagement.ticket

import it.polito.se2.g12.officequeuemanagement.counter.Counter
import it.polito.se2.g12.officequeuemanagement.service.Service
import jakarta.persistence.*

import java.util.*

@Entity
class Ticket(
    @ManyToOne
    var service:Service
) {
    @Id
    @GeneratedValue(generator = "uuid2")
    var TicketId: UUID?=null
    var served:Boolean=false
    @ManyToOne
    var counterAssigned:Counter?=null

}