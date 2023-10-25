package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import it.polito.se2.g12.officequeuemanagement.service.toDTO
import java.util.UUID

data class CounterDTO (
    val counterId: UUID,
    var number: Int,
    var listOfServices: List<ServiceDTO>,
    var description: String
)
fun Counter.toDTO(): CounterDTO {
    return CounterDTO(this.counterId!!,this.number!!,this.listOfServices!!.map(){it.toDTO()},this.description!!)
}