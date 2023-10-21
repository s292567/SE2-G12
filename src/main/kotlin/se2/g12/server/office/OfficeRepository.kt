package se2.g12.server.office

import org.springframework.data.jpa.repository.JpaRepository


interface OfficeRepository : JpaRepository<Office, Long>