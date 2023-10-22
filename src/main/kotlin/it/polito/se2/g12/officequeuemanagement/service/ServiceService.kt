package it.polito.se2.g12.officequeuemanagement.service

import java.time.Duration

interface ServiceService {
    fun addNewService(tagName:String, serviceTime: Duration):ServiceDTO
    fun getServiceList(tagNameList: List<String>):List<ServiceDTO>
    fun changeService(tagName: String,newService:ServiceDTO):ServiceDTO

    fun removeService(tagName: String):ServiceDTO
}