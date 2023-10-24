package it.polito.se2.g12.officequeuemanagement.ticket

open class TicketException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class ServiceNotFoundException(message: String) : TicketException(message)

class TicketNotFoundException(message: String) : TicketException(message)

class CounterNotFoundException(message: String) : TicketException(message)
