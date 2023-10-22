package se2.g12.server.ticket

import java.util.UUID

interface TicketService {
    fun createTicket(serviceName:String):TicketDTO
    fun getTicketInfo(ticketId:Long):TicketDTO

    fun assignCounter(ticketId: Long, counterId:UUID):TicketDTO
    fun setServed(ticketId: Long):TicketDTO
}