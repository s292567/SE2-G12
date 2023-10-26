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
import java.time.Duration
import java.util.*

class TicketServiceImplTest {
    private val ticketRepository : TicketRepository = mockk()
    private val serviceRepository : ServiceRepository= mockk()
    private val counterRepository : CounterRepository= mockk()
    private val mockService = Service("test service", Duration.ZERO, "Service mock")
    private val mockCounter : Counter = mockk()
    private val uuid = UUID.randomUUID()
    private var mockTicket = Ticket(mockService)
        /*mockk<Ticket> {
        every { service } returns mockService
        every { TicketId } returns uuid
        every { served } returns false
        every { counterAssigned } returns null
    }*/

    private val ticketService = TicketServiceImpl(ticketRepository, serviceRepository, counterRepository)


    /// NEW TICKET TESTS
    @Test
    fun `should create a new ticket`() {
        every { serviceRepository.findByServiceName(any<String>()) } returns listOf( mockService )
        every { ticketRepository.save(any()) } returns mockTicket

        val ticketDTO = ticketService.createTicket("test service")

        verify (exactly = 1) { serviceRepository.findByServiceName("test service") }
        verify (exactly = 1) { ticketRepository.save(any()) }

        assertEquals(mockTicket.service.tagName, ticketDTO.serviceType)

    }

    @Test
    fun `should throw an error is service is not provided`(){
        every { serviceRepository.findByServiceName("") } returns emptyList()

        assertThrows<ServiceNotFoundException>{
            ticketService.createTicket("")
        }
        verify (exactly = 1) { serviceRepository.findByServiceName("") }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }

    /// GET TICKET INFO TESTS
    @Test
    fun `should return the ticket info`() {
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)

        val ticketDTO = ticketService.getTicketInfo(uuid)

        verify (exactly = 1) { ticketRepository.findById(uuid) }
        assertEquals(mockTicket.service.tagName, ticketDTO.serviceType)
    }
    @Test
    fun `should throw error if ticket doesn't exist`() {
        every { ticketRepository.findById(any()) } returns Optional.empty()

        assertThrows<TicketNotFoundException> {
            ticketService.getTicketInfo(uuid)
        }
        verify (exactly = 1) { ticketRepository.findById(uuid) }
    }

    /// ASSIGN COUNTER TESTS
    @Test
    fun `should assign a counter to the ticket`() {
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { counterRepository.findByNumber(any()) } returns listOf(mockCounter)
        every { ticketRepository.save(any()) } answers { it.invocation.args[0] as Ticket }

        val result = ticketService.assignCounter(uuid, 1)

        assertNotNull(result)
        assertEquals(mockTicket.TicketId, result.ticketId)
        assertEquals(mockTicket.service.tagName, result.serviceType)
        assertEquals(mockTicket.served, result.served)
        assertEquals(mockTicket.counterAssigned, result.counterAssigned)

        verify { ticketRepository.findById(uuid) }
        verify { counterRepository.findByNumber(1) }
        verify { ticketRepository.save(any()) }
    }
    @Test
    fun `should throw error if the counter doesn't exist`() {
        val uuid = UUID.randomUUID()
        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { counterRepository.findByNumber(any()) } returns emptyList()

        assertThrows<CounterNotFoundException> { ticketService.assignCounter(uuid, 1) }

        verify (exactly = 1) { ticketRepository.findById(uuid) }
        verify (exactly = 1) { counterRepository.findByNumber(1) }
        verify (exactly = 0) { ticketRepository.save(any()) }

    }
    @Test
    fun `should throw error if the ticket doesn't exist`() {
        val uuid = UUID.randomUUID()
        every { ticketRepository.findById(uuid) } returns Optional.empty()

        assertThrows<TicketNotFoundException> { ticketService.assignCounter(uuid, 1) }

        verify (exactly = 1) { ticketRepository.findById(uuid) }
        verify (exactly = 0) { counterRepository.findByNumber(1) }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }
    /// SERVED TESTS
    @Test
    fun `should set the ticket to served`() {

        every { ticketRepository.findById(any()) } returns Optional.of(mockTicket)
        every { ticketRepository.save(any()) } answers { it.invocation.args[0] as Ticket }

        val ticketDTO = ticketService.setServed(uuid)

        verify (exactly = 1) { ticketRepository.findById(uuid) }
        verify (exactly = 1) { ticketRepository.save(any()) }
        assert(ticketDTO.served)
    }
    @Test
    fun `should throw an error if ticket not found`() {
        every { ticketRepository.findById(any()) } returns Optional.empty()

        assertThrows<TicketNotFoundException> { ticketService.setServed(uuid) }

        verify (exactly = 1) { ticketRepository.findById(uuid) }
        verify (exactly = 0) { ticketRepository.save(any()) }
    }

}