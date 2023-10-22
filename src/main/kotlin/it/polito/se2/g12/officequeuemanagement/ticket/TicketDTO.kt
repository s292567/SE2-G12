package it.polito.se2.g12.officequeuemanagement.ticket

import it.polito.se2.g12.officequeuemanagement.counter.Counter
import java.util.*

data class TicketDTO (
    val ticketId: UUID?,
    val serviceType:String,
    var served:Boolean,
    var counterAssigned: Counter?
)