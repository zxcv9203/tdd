package io.hhplus.tdd.point.stub

import io.hhplus.tdd.point.PointHistory
import io.hhplus.tdd.point.TransactionType

object PointHistoryFixture {
    private val defaultPointHistories = mutableListOf(
        PointHistory(1, 1, TransactionType.CHARGE, 100, System.currentTimeMillis()),
        PointHistory(2, 1, TransactionType.USE, 100, System.currentTimeMillis()),
        PointHistory(3, 2, TransactionType.USE, 100, System.currentTimeMillis()),
        PointHistory(4, 2, TransactionType.USE, 100, System.currentTimeMillis()),
    )

    fun preparePointHistories(pointHistories: MutableList<PointHistory> = defaultPointHistories): MutableList<PointHistory> {
        return pointHistories
    }

    fun filterByUserId(userId: Long): List<PointHistory> {
        return preparePointHistories().filter { it.userId == userId }
    }

    fun getExistsUserId(): Long {
        return preparePointHistories().first().userId
    }
}