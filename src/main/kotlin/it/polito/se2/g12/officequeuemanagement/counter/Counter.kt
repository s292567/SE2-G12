package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.Service
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

import java.util.UUID
@Entity
class Counter (
    var number: Int?=null,
){
    @Id
    @GeneratedValue(generator = "uuid2")
    val counterId:UUID?=null
    @ManyToMany
    private val listOfServices:List<Service> ?=null;



}