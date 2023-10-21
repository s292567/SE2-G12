package se2.g12.server.service

import java.time.Duration

data class ServiceDTO (
    var serviceId:Long?,
    var tagName:String,
    var serviceTime: Duration
)