package io.hhplus.tdd.point

data class PointHistory(
    val id: Long,
    val userId: Long,
    val type: TransactionType,
    val amount: Long,
    val timeMillis: Long,
) {
    companion object {
        fun createByCharge(userId: Long, amount: Long): PointHistory {
            return PointHistory(0, userId, TransactionType.CHARGE, amount, System.currentTimeMillis())
        }

        fun createByUse(id: Long, amount: Long): PointHistory {
            return PointHistory(0, id, TransactionType.USE, amount, System.currentTimeMillis())
        }
    }
}

/**
 * 포인트 트랜잭션 종류
 * - CHARGE : 충전
 * - USE : 사용
 */
enum class TransactionType {
    CHARGE, USE
}