package it.polito.se2.g12.officequeuemanagement.ticket

import it.polito.se2.g12.officequeuemanagement.counter.Counter
import it.polito.se2.g12.officequeuemanagement.service.Service
import jakarta.persistence.*

@Entity
class Ticket(
    @OneToOne
    var service:Service
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var TicketId:Long?=null
    var served:Boolean=false
    @OneToOne
    var counterAssigned:Counter?=null

}