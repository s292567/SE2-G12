package it.polito.se2.g12.officequeuemanagement.admin

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.contains
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.polito.se2.g12.officequeuemanagement.counter.CounterDTO
import it.polito.se2.g12.officequeuemanagement.counter.CounterService
import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import it.polito.se2.g12.officequeuemanagement.ticket.TicketDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class AdminController(private val counterService:CounterService) {


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
    fun addNewCounter(@RequestBody number:Int, tagNameList: List<String>,description:String):CounterDTO{
        return counterService.addNewCounter(number, tagNameList, description )
    }
    @PostMapping("/API/admin/counter/changeServiceList/")
    @ResponseStatus(HttpStatus.OK)
    fun changeCounterServices(@RequestBody number:Int, tagNameList: List<String>):CounterDTO{
        return counterService.changeCounterServices(number,tagNameList)
    }



    // Service to do list (globally usable services)
    // TODO: make entry point get all service
    // TODO: make entry point post add new service, change duration of service , delete a service

}