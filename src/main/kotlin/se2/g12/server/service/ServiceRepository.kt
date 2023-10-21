package se2.g12.server.service

import org.springframework.data.jpa.repository.JpaRepository


interface ServiceRepository : JpaRepository<Service, Long>