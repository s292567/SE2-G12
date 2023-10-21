package se2.g12.server.ticket

import se2.g12.server.service.ServiceRepository

class TicketServiceImpl(private val ticketRepository: TicketRepository,serviceRepository: ServiceRepository):TicketService {
    override fun createIssue(serviceName: String): TicketDTO {

        TODO("Not yet implemented")
    }

    override fun getTicketInfo(ticketId: Long): TicketDTO {
        TODO("Not yet implemented")
    }
}