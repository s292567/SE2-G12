package se2.g12.server.ticket

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class TicketController {

    // Ticket to do list
    // TODO: make entry point get ticket information
    // TODO: make entry point post createTicket,assignCounter,setServed
@PostMapping("/API/ticket/create")
@ResponseStatus(HttpStatus.OK)
fun createTicket(@RequestBody service: String){
    // TODO:("Not Yet Impl")
}



}