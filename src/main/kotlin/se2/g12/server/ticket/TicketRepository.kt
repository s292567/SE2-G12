package se2.g12.server.ticket

import org.springframework.data.jpa.repository.JpaRepository


interface TicketRepository : JpaRepository<Ticket, Long>