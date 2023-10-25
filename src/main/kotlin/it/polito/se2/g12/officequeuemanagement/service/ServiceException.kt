package it.polito.se2.g12.officequeuemanagement.service

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ServiceException: ResponseEntityExceptionHandler() {
    @ExceptionHandler(ServiceTagNameMissingException::class)
    fun serviceTagNameMissingException(e: ServiceTagNameMissingException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.NOT_FOUND, e.message!! )
    @ExceptionHandler(ServiceNameAlreadyUsed::class)
    fun serviceNameAlreadyUsed(e: ServiceNameAlreadyUsed) = ProblemDetail
        .forStatusAndDetail( HttpStatus.CONFLICT, e.message!! )
    @ExceptionHandler(ServiceInUseException::class)
    fun serviceInUse(e: ServiceInUseException) = ProblemDetail
        .forStatusAndDetail( HttpStatus.BAD_REQUEST, e.message!! )
}

class ServiceTagNameMissingException(message: String?) : Throwable(message)
class ServiceNameAlreadyUsed(message: String?) : Throwable(message)
class ServiceInUseException(message: String?) : Throwable(message)