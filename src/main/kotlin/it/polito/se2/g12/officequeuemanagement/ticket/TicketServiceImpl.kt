package it.polito.se2.g12.officequeuemanagement.ticket

import it.polito.se2.g12.officequeuemanagement.service.ServiceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TicketServiceImpl(private val ticketRepository: TicketRepository,private val serviceRepository: ServiceRepository):TicketService {
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