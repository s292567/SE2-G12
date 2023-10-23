package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import it.polito.se2.g12.officequeuemanagement.service.ServiceRepository
import it.polito.se2.g12.officequeuemanagement.service.toDTO
import org.springframework.stereotype.Service

@Service
class CounterServiceImpl(private val counterRepository: CounterRepository):CounterService {
    override fun addNewCounter(number: Int, tagNameList: List<String>): CounterDTO {
        TODO("Not yet implemented")
    }

    override fun getCounterService(number: Int): List<ServiceDTO> {
        return counterRepository.findByNumber(number).first().listOfServices!!.map(){it.toDTO()}

    }

    override fun changeCounterServices(number: Int, tagNameList: List<String>): CounterDTO {
        TODO("Not yet implemented")
    }

    override fun getAllCounter(): List<Counter> {
        TODO("Not yet implemented")
    }
}