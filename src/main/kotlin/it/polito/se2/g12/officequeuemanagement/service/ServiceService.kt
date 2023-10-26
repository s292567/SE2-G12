package it.polito.se2.g12.officequeuemanagement.service

import it.polito.se2.g12.officequeuemanagement.counter.CounterDTO
import java.time.Duration

interface ServiceService {
    fun addNewService(tagName:String, serviceTime: Duration,description:String):ServiceDTO
    fun getAllService():List<ServiceDTO>
    fun changeService(tagName: String, newName:String, newServiceTime:Duration, newDescription:String):ServiceDTO
    fun removeService(tagName: String):ServiceDTO
    fun getServiceCounterList(tagName: String):List<CounterDTO>
    fun getServiceCounters():List<CounterDTO>
}