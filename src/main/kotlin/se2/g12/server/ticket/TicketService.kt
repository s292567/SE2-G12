package se2.g12.server.ticket

interface TicketService {
    fun createIssue(serviceName:String):TicketDTO
    fun getTicketInfo(ticketId:Long):TicketDTO
}