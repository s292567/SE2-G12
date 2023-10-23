package it.polito.se2.g12.officequeuemanagement.counter

import it.polito.se2.g12.officequeuemanagement.service.Service
import it.polito.se2.g12.officequeuemanagement.service.ServiceDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CounterRepository : JpaRepository<Counter, UUID> {
    @Query("select * from counter where number= :number ", nativeQuery = true)
    fun findByNumber(number: Int?): List<Counter>
}
