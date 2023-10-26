package it.polito.se2.g12.officequeuemanagement.service


import java.time.Duration
import java.util.*

data class ServiceDTO (
    var serviceId: UUID?,
    var tagName:String,
    var serviceTime: Duration?,
    var description: String,
)
fun Service.toDTO(): ServiceDTO {
    return ServiceDTO(this.serviceId,this.tagName,this.serviceTime, this.description)
}