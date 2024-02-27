package test.woodo.booking.consignment.domain

import org.springframework.data.jpa.domain.Specification

object ConsignmentSpecification {
    fun of(status: Status?): Specification<Consignment> {
        return Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get<Consignment>("status"), status).takeIf { status != null }
        }
    }
}
