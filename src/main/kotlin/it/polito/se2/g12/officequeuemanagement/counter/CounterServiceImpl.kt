package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.*
import it.polito.se2.g12.officequeuemanagement.ticket.ServiceNotFoundException


@org.springframework.stereotype.Service
class CounterServiceImpl(
    private val counterRepository: CounterRepository,
    private val serviceService: ServiceService,
    private val serviceRepository: ServiceRepository
) : CounterService {

    override fun addNewCounter(number: Int, tagNameList: List<String>, description: String): CounterDTO {
        if (counterRepository.findByNumber(number).isNotEmpty())
            throw CounterNumberAlreadyUsed("number $number is already used")
        var list = mutableListOf<Service>()
        for (el in tagNameList) {
            if (serviceRepository.findByServiceName(el).isEmpty())
                throw ServiceTagNameMissingException("Tag-Name $el is not present in the Service DB")
            list.add(serviceRepository.findByServiceName(el).first())
        }
        var counter: Counter = Counter(number, list, description)
        return counterRepository.save(counter).toDTO()
    }

    override fun getCounterService(number: Int): List<ServiceDTO> {

        val ret = counterRepository.findByNumber(number)
        if (ret.isEmpty())
            throw CounterMissing("counter $number is not present in the db")
        return ret.first().listOfServices!!.map() { it.toDTO() }
    }

    override fun getCounterInfo(number: Int): CounterDTO {
        val ret = counterRepository.findByNumber(number)
        if (ret.isEmpty())
            throw CounterMissing("counter $number is not present in the db")
        return ret.first().toDTO()
    }

    override fun changeCounterServices(number: Int, tagNameList: List<String>): CounterDTO {
        var list = mutableListOf<Service>()
        for (el in tagNameList) {
            if (serviceRepository.findByServiceName(el).isEmpty())
                throw ServiceTagNameMissingException("Tag-Name $el is not present in the Service DB")
            list.add(serviceRepository.findByServiceName(el).first())

        }
        var uuid = counterRepository.findByNumber(number).first().counterId!!
        var old = counterRepository.getReferenceById(uuid)
        old.listOfServices = list
        counterRepository.deleteById(uuid)
        return counterRepository.save(old).toDTO()
    }

    override fun getAllCounter(): List<CounterDTO> {
        return counterRepository.findAll().map() { it.toDTO() }
    }

    override fun deleteCounter(number: Int) {


        var old = counterRepository.findByNumber(number)
        if (old.isEmpty())
            throw CounterMissing("counter $number is not present in the db")
        var element = old.first()
        counterRepository.deleteById(element.counterId!!)


    }
}