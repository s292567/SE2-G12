package it.polito.se2.g12.officequeuemanagement.service

import java.time.Duration

data class ServiceDTO (
    var serviceId:Long?,
    var tagName:String,
    var duration: Duration?,
)