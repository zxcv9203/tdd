package io.hhplus.tdd.point.fake

import io.hhplus.tdd.point.PointHistory
import io.hhplus.tdd.point.PointHistoryRepository

class FakePointHistoryRepository(
    private val pointHistories: List<PointHistory>
): PointHistoryRepository {
    override fun findHistoriesByUserId(userId: Long): List<PointHistory> {
        return pointHistories.filter { it.userId == userId }
    }
}