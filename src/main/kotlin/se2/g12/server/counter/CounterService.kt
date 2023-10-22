package se2.g12.server.counter

import se2.g12.server.service.ServiceDTO
import java.time.Duration

interface CounterService {
    fun addNewCounter(number:Int, tagNameList: List<String>):CounterDTO
    fun getCounterService(number:Int):List<ServiceDTO>
    fun changeCounterServices(number: Int,tagNameList: List<String>):CounterDTO
    fun getAllCounter():List<Counter>

}