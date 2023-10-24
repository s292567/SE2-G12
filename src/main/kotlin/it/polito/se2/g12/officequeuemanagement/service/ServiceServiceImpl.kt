package it.polito.se2.g12.officequeuemanagement.service

import it.polito.se2.g12.officequeuemanagement.counter.CounterDTO
import it.polito.se2.g12.officequeuemanagement.counter.CounterRepository
import it.polito.se2.g12.officequeuemanagement.counter.toDTO
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.Duration
import java.util.*

@Service
class ServiceServiceImpl(private val serviceRepository: ServiceRepository,private val counterRepository: CounterRepository) :ServiceService{
    override fun addNewService(tagName: String, serviceTime: Duration, description: String): ServiceDTO {
        if(serviceRepository.findByServiceName(tagName).isEmpty())
        return serviceRepository.save(Service(tagName,serviceTime,description)).toDTO()
        else
            throw Exception("Tag-name already present")
    }
    fun <T> Optional<T>.unwrap(): T? = orElse(null)
    override fun getAllService(tagNameList: List<String>): List<ServiceDTO> {
        var list= mutableListOf<ServiceDTO>()
        for (el in tagNameList){
            if(serviceRepository.findByServiceName(el).isEmpty())
                throw Exception("One tag-Name is not present in the Service DB")
            list.add(serviceRepository.findByServiceName(el).first().toDTO())
        }
        return list
    }

    override fun changeService(
        tagName: String,
        newName: String,
        newServiceTime: Duration,
        newDescription: String
    ): ServiceDTO {
        var old=serviceRepository.findByServiceName(tagName).first()
        if (serviceRepository.findByServiceName(newName).isNotEmpty())
            throw Exception("new Name not available")
        old.description=newDescription
        old.serviceTime=newServiceTime
        old.tagName=newName
        return serviceRepository.save(old).toDTO()
    }

    override fun removeService(tagName: String): ServiceDTO {
        var old=serviceRepository.findByServiceName(tagName).first()
        if( old.serviceId==null){throw Exception ("tagname not present in the db")}
        serviceRepository.deleteById(old.serviceId!!)
        return old.toDTO()
    }

    override fun getServiceCounterList(tagName: String): List<CounterDTO> {
        return serviceRepository.findCounterList(serviceRepository.findByServiceName(tagName).first().serviceId!!).map(){counterRepository.findById(it).unwrap()!!.toDTO()}

    }


}