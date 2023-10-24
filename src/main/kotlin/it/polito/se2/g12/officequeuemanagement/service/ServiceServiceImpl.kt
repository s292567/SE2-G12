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
    //fun <T> Optional<T>.unwrap(): T? = orElse(null)
    override fun addNewService(tagName: String, serviceTime: Duration, description: String): ServiceDTO {
        if(serviceRepository.findByServiceName(tagName).isEmpty())
        return serviceRepository.save(Service(tagName,serviceTime,description)).toDTO()
        else
            throw Exception("Tag-name already present")
    }

    override fun getAllService(): List<ServiceDTO> {
           return serviceRepository.findAll().map(){it.toDTO()}
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
        if( old.serviceId==null){throw Exception ("tagName not present in the db")}
        serviceRepository.deleteById(old.serviceId!!)
        return old.toDTO()
    }

    override fun getServiceCounterList(tagName: String): List<CounterDTO> {
        var list=counterRepository.findAll().filter{ it.listOfServices!!.contains(serviceRepository.findByServiceName(tagName).first()) }
        return list.map{it.toDTO()}
    }


}