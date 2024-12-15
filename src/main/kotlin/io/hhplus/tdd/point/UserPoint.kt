package io.hhplus.tdd.point

data class UserPoint(
    val id: Long,
    val point: Long,
    val updateMillis: Long,
) {

    fun charge(amount: Long): UserPoint {
        require(amount > 0) { PointErrorMessage.CHARGE_AMOUNT_SHOULD_BE_POSITIVE.message }
        require(this.point + amount <= MAX_POINT) { PointErrorMessage.EXCEED_MAX_POINT.message }
        return UserPoint(id, this.point + amount, this.updateMillis)
    }

    fun use(amount: Long): UserPoint {
        require(amount > 0) { PointErrorMessage.USE_AMOUNT_SHOULD_BE_POSITIVE.message }
        require(this.point - amount >= 0) { PointErrorMessage.NOT_ENOUGH_POINT.message }
        return UserPoint(id, this.point - amount, this.updateMillis)
    }

    companion object {
        private const val MAX_POINT = 2_000_000L
    }
}
