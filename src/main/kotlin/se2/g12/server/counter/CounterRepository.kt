package se2.g12.server.counter

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CounterRepository : JpaRepository<Counter, UUID>