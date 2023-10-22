package it.polito.se2.g12.officequeuemanagement.ticket

import java.util.*

interface TicketService {
    fun createTicket(serviceName:String):TicketDTO
    fun getTicketInfo(ticketId:Long):TicketDTO

    fun assignCounter(ticketId: Long, counterId: UUID):TicketDTO
    fun setServed(ticketId: Long):TicketDTO
}