package it.polito.se2.g12.officequeuemanagement.ticket

import it.polito.se2.g12.officequeuemanagement.counter.CounterRepository
import it.polito.se2.g12.officequeuemanagement.service.ServiceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TicketServiceImpl(
        private val ticketRepository: TicketRepository,
        private val serviceRepository: ServiceRepository,
        private val counterRepository: CounterRepository
) : TicketService {

    override fun createTicket(serviceName: String): TicketDTO {
        // Retrieve the service by name from the serviceRepository
        val services = serviceRepository.findByServiceName(serviceName)
        if (services.isNotEmpty()) {
            val service = services.first()
            // Create a new Ticket
            val ticket = Ticket(service = service)
            // Save the ticket to the repository
            val savedTicket = ticketRepository.save(ticket)
            return TicketDTO(
                    ticketId = savedTicket.TicketId,
                    serviceType = serviceName,
                    served = savedTicket.served,
                    counterAssigned = savedTicket.counterAssigned
            )

        } else {
            throw ServiceNotFoundException("Service not found for name: $serviceName")
        }

    }

    override fun getTicketInfo(ticketId: UUID): TicketDTO {
        // Retrieve the ticket by ID from the repository
        val ticket = ticketRepository.findById(ticketId)
                .orElseThrow { TicketNotFoundException("Ticket not found for ID: $ticketId") }

        return TicketDTO(
                ticketId = ticket.TicketId,
                serviceType = ticket.service.tagName,
                served = ticket.served,
                counterAssigned = ticket.counterAssigned
        )
    }

    override fun assignCounter(ticketId: UUID, counterId: UUID): TicketDTO {
        // Retrieve the ticket by ID from the repository
        val ticket = ticketRepository.findById(ticketId)
                .orElseThrow { TicketNotFoundException("Ticket not found for ID: $ticketId") }

        // Retrieve the counter by ID from the repository
        val counter = counterRepository.findById(counterId)
                .orElseThrow { CounterNotFoundException("Counter not found for ID: $counterId") }

        // Assign the counter to the ticket
        ticket.counterAssigned = counter

        // Save the updated ticket
        val updatedTicket = ticketRepository.save(ticket)

        return TicketDTO(
                ticketId = updatedTicket.TicketId,
                serviceType = updatedTicket.service.tagName,
                served = updatedTicket.served,
                counterAssigned = updatedTicket.counterAssigned
        )
    }

    override fun setServed(ticketId: UUID): TicketDTO {
        // Retrieve the ticket by ID from the repository
        val ticket = ticketRepository.findById(ticketId)
                .orElseThrow { TicketNotFoundException("Ticket not found for ID: $ticketId") }

        // Mark the ticket as served
        ticket.served = true

        // Save the updated ticket
        val updatedTicket = ticketRepository.save(ticket)

        return TicketDTO(
                ticketId = updatedTicket.TicketId,
                serviceType = updatedTicket.service.tagName,
                served = updatedTicket.served,
                counterAssigned = updatedTicket.counterAssigned
        )
    }
}

