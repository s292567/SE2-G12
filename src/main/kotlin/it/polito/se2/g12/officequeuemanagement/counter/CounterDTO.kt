package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import java.util.UUID

data class CounterDTO (
    val counterId: UUID,
    var number: Int,
    var listOfServices: List<ServiceDTO>
)
