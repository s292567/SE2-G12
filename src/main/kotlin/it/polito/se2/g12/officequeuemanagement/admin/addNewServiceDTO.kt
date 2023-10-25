package it.polito.se2.g12.officequeuemanagement.admin

import java.time.Duration

data class AddNewServiceDTO(
    var tagName:String,
    var serviceTime: Duration,
    var description:String)