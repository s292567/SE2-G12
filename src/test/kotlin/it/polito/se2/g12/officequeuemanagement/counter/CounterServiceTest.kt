package it.polito.se2.g12.officequeuemanagement.counter

import io.mockk.*
import it.polito.se2.g12.officequeuemanagement.service.Service
import it.polito.se2.g12.officequeuemanagement.service.ServiceRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.util.*

class CounterServiceTest {
    private val counterRepository : CounterRepository= mockk()
    private val serviceRepository : ServiceRepository = mockk()
    private val counterService = CounterServiceImpl(counterRepository, serviceRepository)
    private val service1 = Service("service 1", Duration.ofMinutes(100), "test description")
    private val service2 = Service("service 2", Duration.ofMinutes(40), "test description 2")
    private val uuid = UUID.randomUUID()
    private val mockedCounter = mockk<Counter> {
        every { number } returns 2
        every { description } returns "Mocked CounterDTO"
        every { counterId } returns uuid
        every { listOfServices } returns listOf(service1, service2)
    }

    /// NEW COUNTER TESTS
    @Test
    fun `should create a new counter`() {
        every { counterRepository.findByNumber(any()) } returns listOf()
        every { serviceRepository.findByServiceName(any()) } returns listOf(service1)
        every { counterRepository.save(any()) } returns mockedCounter

        val newCounter = counterService.addNewCounter(2, listOf("service 1"),"New counter")
        verify (exactly = 1) { counterRepository.findByNumber(2) }
        verify (exactly = 2) { serviceRepository.findByServiceName("service 1") }
        verify (exactly = 1) { counterRepository.save(any()) }
        assertEquals(mockedCounter.description, newCounter.description)
    }

    /// GET COUNTER SERVICES TESTS
    @Test
    fun `should return a list of services`() {
        every { counterRepository.findByNumber(1) } returns listOf( mockedCounter)

        val services = counterService.getCounterService(1)

        verify (exactly = 1) { counterRepository.findByNumber(1) }

        assertEquals(mockedCounter.listOfServices!![0].tagName, services[0].tagName)
        assertEquals(mockedCounter.listOfServices!![1].tagName, services[1].tagName)
    }
    @Test
    fun `should throw error if counter doesn't exist`() {
        every { counterRepository.findByNumber(0) } returns listOf()
        assertThrows<CounterMissing>{
            counterService.getCounterService(0)
        }

        verify (exactly = 1) { counterRepository.findByNumber(0) }
    }

    @Test
    fun `should get the counter info`(){
        every { counterRepository.findByNumber(any()) } returns listOf( mockedCounter )
        val counterDTO = counterService.getCounterInfo(1)

        assertEquals(mockedCounter.counterId, counterDTO.counterId)
        assertEquals(mockedCounter.description, counterDTO.description)
    }


    /// LIST OF ALL COUNTERS
    @Test
    fun `should return the list of all counters`() {
        // return counterRepository.findAll().map() { it.toDTO() }
        every { counterRepository.findAll() } returns listOf(mockedCounter, mockedCounter)
        val counters = counterService.getAllCounter()

        assert(counters.size == 2)
        assertEquals(mockedCounter.description, counters.first().description)
    }

    @Test
    fun `should delete a counter`(){
        every { counterRepository.findByNumber(any()) } returns listOf(mockedCounter)
        every { counterRepository.deleteById(any()) } just Runs
        counterService.deleteCounter(1)
    }
}