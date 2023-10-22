package it.polito.se2.g12.officequeuemanagement.counter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CounterRepository : JpaRepository<Counter, UUID>