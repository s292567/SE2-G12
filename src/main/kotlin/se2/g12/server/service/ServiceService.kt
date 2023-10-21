package se2.g12.server.service

import java.time.Duration

interface ServiceService {
    fun addNewService(tagName:String, serviceTime: Duration)
    fun getServiceList(tagNameList: List<String>)

}