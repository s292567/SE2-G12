package it.polito.se2.g12.officequeuemanagement.admin

import it.polito.se2.g12.officequeuemanagement.counter.CounterService
import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import it.polito.se2.g12.officequeuemanagement.ticket.TicketDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class AdminController(private val counterService:CounterService) {
    // Counter to do list
    // TODO: make entry point get all counter,get single counter service list:List<ServiceDTO>
    // TODO: make entry point post add new counter:CounterDTO,change counter service list
    // Service to do list (globally usable services)
    // TODO: make entry point get all service
    // TODO: make entry point post add new service, change duration of service , delete a service
    @GetMapping("/API/admin/counter/{number}")
    fun getCounterInfo(@PathVariable number: Int):ServiceDTO {
        val counterServiceList = counterService.getCounterService(number)
        println(counterServiceList.first())
        println("eccolo");
        return counterServiceList.first()
    }
}