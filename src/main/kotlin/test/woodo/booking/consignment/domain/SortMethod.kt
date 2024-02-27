package test.woodo.booking.consignment.domain

import org.springframework.data.domain.Sort

enum class SortMethod(val sort: Sort) {
    MANY(Sort.by("rentals").descending()),

    LOW_PRICE(Sort.by("price").ascending()),

    RECENT(Sort.by("created_at").descending()),
}
