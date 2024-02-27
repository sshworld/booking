package test.woodo.booking.consignment.domain

enum class Status {
    RENTING,

    RETURN,
    ;

    fun canRent() = this == RETURN
}
