package se2.g12.server.counter

import se2.g12.server.service.Service
import java.time.Duration
import java.util.*

class CounterDTO {
    data class ServiceDTO (
        val counterId: UUID,
        var number: Int,
        var listOfServices: List<Service>
    )
}