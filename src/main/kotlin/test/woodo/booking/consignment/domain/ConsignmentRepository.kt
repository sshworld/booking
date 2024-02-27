package test.woodo.booking.consignment.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ConsignmentRepository : JpaRepository<Consignment, Long>, JpaSpecificationExecutor<Consignment>
