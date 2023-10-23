package it.polito.se2.g12.officequeuemanagement.counter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.polito.se2.g12.officequeuemanagement.service.Service
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.Duration

class CounterServiceImplTest {
    private val counterRepository : CounterRepository= mockk()
    private val counterService = CounterServiceImpl(counterRepository)
    private val mockServices : list<Service> = listOf(Service("service 1", Duration.ofMinutes(100)), "service 2", Duration.ofMinutes(40))
    private val mockCounterWithServices = Counter(number = 1, listOfServices=mockServices)

    /// NEW COUNTER TESTS
    @Test
    fun `should create a new counter`() {
        TODO("To compete")
        counterService.addNewCounter(0, listOf("test"))
    }

    /// GET COUNTER SERVICES TESTS
    @Test
    fun `should return a list of services`() {
        every { counterRepository.findByNumber(1) } returns listOf( mockCounterWithServices)
        val services = counterService.getCounterService(1)

        verify (exactly = 1) { counterRepository.findByNumber(1) }
        assertEquals(services, mockServices, "the return services should match the services associated with the counter")
    }
    @Test
    fun `should throw error if counter doesn't exist`() {
        every { counterRepository.findByNumber(1) } returns listOf()
        assertFails("Expected to throw an error") {
            counterService.getCounterService(0)
        }
        verify (exactly = 1) { counterRepository.findByNumber(0) }
    }

    /// CHANGE COUNTER SERVICES TESTS
    @Test
    fun `should change the services of a counter`() {
    }

    /// LIST OF ALL COUNTERS
    @Test
    fun `should return the list of all counters`() {
    }
}