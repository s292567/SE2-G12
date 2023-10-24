package it.polito.se2.g12.officequeuemanagement.service

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.Duration

@Entity
class Service (
    var tagName:String,
    var serviceTime: Duration,
    var description:String
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var serviceId:Long?=null
    //counterList

}