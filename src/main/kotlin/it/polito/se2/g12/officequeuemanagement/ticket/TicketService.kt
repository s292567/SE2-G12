package it.polito.se2.g12.officequeuemanagement.ticket

import java.util.*

interface TicketService {
    fun createTicket(serviceName:String):TicketDTO
    fun getTicketInfo(ticketId:UUID):TicketDTO

    fun assignCounter(ticketId: UUID, counterId: UUID):TicketDTO
    fun setServed(ticketId: UUID):TicketDTO
}