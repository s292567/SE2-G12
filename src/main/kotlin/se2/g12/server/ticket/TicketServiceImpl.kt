package se2.g12.server.ticket

import se2.g12.server.service.ServiceRepository
import java.util.*

class TicketServiceImpl(private val ticketRepository: TicketRepository,serviceRepository: ServiceRepository):TicketService {
    override fun createTicket(serviceName: String): TicketDTO {

        TODO("Not yet implemented")
    }

    override fun getTicketInfo(ticketId: Long): TicketDTO {
        TODO("Not yet implemented")
    }

    override fun assignCounter(ticketId: Long, counterId: UUID): TicketDTO {
        TODO("Not yet implemented")
    }

    override fun setServed(ticketId: Long): TicketDTO {
        TODO("Not yet implemented")
    }
}