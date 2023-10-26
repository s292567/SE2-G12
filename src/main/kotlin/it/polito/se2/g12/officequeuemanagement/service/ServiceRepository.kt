package it.polito.se2.g12.officequeuemanagement.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ServiceRepository : JpaRepository<Service, UUID>{
    @Query("select * from service where tag_name= :tag_name ", nativeQuery = true)
    fun findByServiceName(tag_name: String?): List<Service>
    //@Query("select * from counter_list_of_services where listOfServiceId=:serviceId", nativeQuery = true)
    //fun findCounterList(serviceId:Long):List<UUID>
}