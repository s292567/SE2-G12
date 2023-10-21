package se2.g12.server.ticket

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class TicketController {
@PostMapping("/API/ticket/create")
@ResponseStatus(HttpStatus.OK)
fun createTicket(@RequestBody service: String){
    // TODO:("Not Yet Impl")
}



}