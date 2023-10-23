package it.polito.se2.g12.officequeuemanagement.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository : JpaRepository<Service, Long>{
    @Query("select * from message where tagname= :tagname ", nativeQuery = true)
    fun findByServiceName(tagname: String?): List<Service>
}