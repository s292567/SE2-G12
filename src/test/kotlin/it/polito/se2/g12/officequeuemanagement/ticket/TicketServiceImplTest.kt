package it.polito.se2.g12.officequeuemanagement.ticket

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.polito.se2.g12.officequeuemanagement.counter.Counter
import it.polito.se2.g12.officequeuemanagement.counter.CounterRepository
import it.polito.se2.g12.officequeuemanagement.service.Service
import it.polito.se2.g12.officequeuemanagement.service.ServiceRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.*

class TicketServiceImplTest {
    private val ticketRepository : TicketRepository = mockk()
    private val serviceRepository : ServiceRepository= mockk()
    private val counterRepository : CounterRepository= mockk()
    private val mockService : Service = mockk()
    private val mockCounter : Counter = mockk()
    private val mockTicket = Ticket(mockService)

    private val ticketService = TicketServiceImpl(ticketRepository, serviceRepository, counterRepository)

    /// NEW TICKET TESTS
    @Test
    fun `should create a new ticket`() {
        every { serviceRepository.getServiceByTagName(any<String>()) } returns mockService
        every { ticketRepository.save(any()) } returns mockTicket

        val ticketDTO = ticketService.createTicket("test service")

        verify (exactly = 1) { serviceRepository.getServiceByTagName("test service") }
        verify (exactly = 1) { ticketRepository.save(any()) }

        assertEquals(mockTicket.service.tagName, ticketDTO.serviceType)

    }
    @Test
    fun `should throw an error is service is not provided`(){
        every { serviceRepository.getServiceByTagName(any<String>()) } returns null

        assertThrows<ServiceNotFoundException>{
            ticketService.createTicket("")
        }
        verify (exactly = 1) { serviceRepository.getServiceByTagName("") }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }

    /// GET TICKET INFO TESTS
    @Test
    fun `should return the ticket info`() {
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)

        val ticketDTO = ticketService.getTicketInfo(1)

        verify (exactly = 1) { ticketRepository.findById(1) }
        assertEquals(mockTicket.service.tagName, ticketDTO.serviceType)
    }
    @Test
    fun `should throw error if ticket doesn't exist`() {
        every { ticketRepository.findById(any()) } returns Optional.empty()

        assertThrows<TicketNotFoundException> {
            ticketService.getTicketInfo(1)
        }
        verify (exactly = 1) { ticketRepository.findById(1) }
    }

    /// ASSIGN COUNTER TESTS
    @Test
    fun `should assign a counter to the ticket`() {
        val uuid = UUID.randomUUID()
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { counterRepository.findById(any()) } returns Optional.of(mockCounter)
        /* the mocked function returns the parameter received */
        every { ticketRepository.save(any()) } answers {
            ticketParam ->
            val ticket = ticketParam as Ticket
            ticket
        }

        val ticketDTO = ticketService.assignCounter(1, uuid)

        verify (exactly = 1) { ticketRepository.findById(1) }
        verify (exactly = 1) { counterRepository.findById(uuid) }
        verify (exactly = 1) { ticketRepository.save(any()) }

        assertEquals(mockCounter, ticketDTO.counterAssigned)
    }
    @Test
    fun `should throw error if the counter doesn't exist`() {
        val uuid = UUID.randomUUID()
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { counterRepository.findById(uuid) } returns Optional.empty()

        assertThrows<CounterNotFoundException> { ticketService.assignCounter(1, uuid) }

        verify (exactly = 1) { ticketRepository.findById(1) }
        verify (exactly = 1) { counterRepository.findById(uuid) }
        verify (exactly = 0) { ticketRepository.save(any()) }

    }
    @Test
    fun `should throw error if the ticket doesn't exist`() {
        val uuid = UUID.randomUUID()
        every { ticketRepository.findById(1) } returns Optional.empty()

        assertThrows<TicketNotFoundException> { ticketService.assignCounter(1, uuid) }

        verify (exactly = 1) { ticketRepository.findById(1) }
        verify (exactly = 0) { counterRepository.findById(uuid) }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }
    /// SERVED TESTS
    @Test
    fun `should set the ticket to served`() {
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { ticketRepository.save(any()) } answers {
                ticketParam ->
            val ticket = ticketParam as Ticket
            ticket
        }

        val ticketDTO = ticketService.setServed(1)

        verify (exactly = 1) { ticketRepository.findById(1) }
        verify (exactly = 1) { ticketRepository.save(any()) }
        assert(ticketDTO.served)
    }
    @Test
    fun `should throw an error if ticket not found`() {
        every { ticketRepository.findById(any()) } returns Optional.empty()

        assertThrows<TicketNotFoundException> { ticketService.setServed(1) }

        verify (exactly = 1) { ticketRepository.findById(1) }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }

}