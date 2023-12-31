package it.polito.se2.g12.officequeuemanagement.admin

import it.polito.se2.g12.officequeuemanagement.counter.CounterDTO
import it.polito.se2.g12.officequeuemanagement.counter.CounterService
import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import it.polito.se2.g12.officequeuemanagement.service.ServiceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class AdminController(private val counterService:CounterService,private val serviceService: ServiceService) {

//to be tested
    @GetMapping("/API/admin/counter/{number}")
    fun getCounterServiceList(@PathVariable number: Int): List<ServiceDTO> {
        return counterService.getCounterService(number)
    }
    @GetMapping("/API/admin/counter/info/{number}")
    fun getCounterInfo(@PathVariable number: Int): CounterDTO {
        return counterService.getCounterInfo(number)
    }
    @GetMapping("/API/admin/counter/info/")
    fun getAllCounter():List<CounterDTO>{
        return counterService.getAllCounter()
    }
    @PostMapping("/API/admin/counter/add/")
    @ResponseStatus(HttpStatus.OK)
    fun addNewCounter(@RequestBody obj:AddNewCounterDTO):CounterDTO{
        return counterService.addNewCounter(obj.number, obj.tagNameList, obj.description!! )
    }
    @PostMapping("/API/admin/counter/changeServiceList/")
    @ResponseStatus(HttpStatus.OK)
    fun changeCounterServices(@RequestBody obj:AddNewCounterDTO):CounterDTO{
        return counterService.changeCounterServices(obj.number,obj.tagNameList)
    }
    @DeleteMapping("/API/admin/counter/delete/{number}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCounter(@PathVariable number: Int) {
        counterService.deleteCounter(number)
    }

    // Service to do list (globally usable services)

    // All tested except getCounterList
    @GetMapping("/API/admin/service/info/")
    fun getAllServices():List<ServiceDTO>{
        return serviceService.getAllService()
    }
    @PostMapping("/API/admin/service/add/")
    fun addNewService(@RequestBody obj:AddNewServiceDTO):ServiceDTO{
        return serviceService.addNewService(obj.tagName,obj.serviceTime,obj.description)
    }
    @PostMapping("/API/admin/service/change/{tagName}")
    fun changeService(@PathVariable tagName:String,@RequestBody obj: AddNewServiceDTO):ServiceDTO{
        return serviceService.changeService(tagName,obj.tagName,obj.serviceTime,obj.description)
    }
    @DeleteMapping("/API/admin/service/delete/{tagName}")
    fun deleteService(@PathVariable tagName: String):ServiceDTO{
        return serviceService.removeService(tagName)
    }

    @GetMapping("/API/admin/service/getCounterList/{tagName}")
    fun getCounterList(@PathVariable tagName: String): List<CounterDTO> {
        return serviceService.getServiceCounterList(tagName);

    }
}