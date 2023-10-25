package it.polito.se2.g12.officequeuemanagement.ticket

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin
class TicketController(
        private val ticketService: TicketService
) {
    @PostMapping("/API/ticket/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTicket(@RequestParam serviceType: String): ResponseEntity<TicketDTO> {
        val createdTicket = ticketService.createTicket(serviceType)
        return ResponseEntity.ok(createdTicket)
    }

    @GetMapping("/API/ticket/{ticketId}")
    fun getTicketInfo(@PathVariable ticketId: UUID): ResponseEntity<TicketDTO> {
        val ticketInfo = ticketService.getTicketInfo(ticketId)
        return if (ticketInfo != null) {
            ResponseEntity.ok(ticketInfo)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/API/ticket/assign/{ticketId}")
    fun assignCounter(
            @PathVariable ticketId: UUID,
            @RequestParam counterId: UUID
    ): ResponseEntity<TicketDTO> {
        val assignedTicket = ticketService.assignCounter(ticketId, counterId)
        return if (assignedTicket != null) {
            ResponseEntity.ok(assignedTicket)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/API/ticket/serve/{ticketId}")
    fun setServed(@PathVariable ticketId: UUID): ResponseEntity<TicketDTO> {
        val servedTicket = ticketService.setServed(ticketId)
        return if (servedTicket != null) {
            ResponseEntity.ok(servedTicket)
        } else {
            ResponseEntity.notFound().build()
        }
    }

}