package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO

interface CounterService {
    fun addNewCounter(number:Int, tagNameList: List<String>):CounterDTO
    fun getCounterService(number:Int):List<ServiceDTO>
    fun changeCounterServices(number: Int,tagNameList: List<String>):CounterDTO
    fun getAllCounter():List<Counter>

}