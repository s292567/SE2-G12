package se2.g12.server.counter

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import se2.g12.server.service.Service
import java.util.UUID
@Entity
class Counter (
    var number: Int?=null,
){
    @Id
    @GeneratedValue(generator = "uuid2")
    val counterId:UUID?=null
    @OneToMany
    private var listOfServices= listOf<Service>()



}