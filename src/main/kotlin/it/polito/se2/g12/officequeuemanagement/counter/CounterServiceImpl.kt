package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.*


@org.springframework.stereotype.Service
class CounterServiceImpl(private val counterRepository: CounterRepository,private val serviceService: ServiceService,private val serviceRepository: ServiceRepository):CounterService {

    override fun addNewCounter(number: Int, tagNameList: List<String>, description: String): CounterDTO {
        if(counterRepository.findByNumber(number).isNotEmpty())
            throw Exception ("number of the counter is already used")
        var list= mutableListOf<Service>()
        for (el in tagNameList){
            if(serviceRepository.findByServiceName(el).isEmpty())
                throw java.lang.Exception("One tag-Name is not present in the Service DB")
            list.add(serviceRepository.findByServiceName(el).first())
        }
        var counter:Counter=Counter(number,list,description)
        return counterRepository.save(counter).toDTO()
    }

    override fun getCounterService(number: Int): List<ServiceDTO> {
        return counterRepository.findByNumber(number).first().listOfServices!!.map(){it.toDTO()}

    }

    override fun getCounterInfo(number: Int): CounterDTO {
        return counterRepository.findByNumber(number).first().toDTO()
    }

    override fun changeCounterServices(number: Int, tagNameList: List<String>): CounterDTO {
        var list= mutableListOf<Service>()
        for (el in tagNameList){
            if(serviceRepository.findByServiceName(el).isEmpty())
                throw java.lang.Exception("One tag-Name is not present in the Service DB")
            list.add(serviceRepository.findByServiceName(el).first())

        }
        var uuid=counterRepository.findByNumber(number).first().counterId!!
        var old=counterRepository.getReferenceById(uuid)
        old.listOfServices=list
        counterRepository.deleteById(uuid)
        return counterRepository.save(old).toDTO()
    }

    override fun getAllCounter(): List<CounterDTO> {
        return counterRepository.findAll().map(){it.toDTO()}
    }

    override fun deleteCounter(number: Int): CounterDTO {

        var old =counterRepository.findByNumber(number)
        if (old.isEmpty())
            throw java.lang.Exception("the counter is ot present in the db")
        else {
            var old=old.first()
            counterRepository.deleteById(old.counterId!!)
            return old.toDTO()
        }

    }
}