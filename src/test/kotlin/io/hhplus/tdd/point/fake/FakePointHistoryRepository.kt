package io.hhplus.tdd.point.fake

import io.hhplus.tdd.point.PointHistory
import io.hhplus.tdd.point.PointHistoryRepository

class FakePointHistoryRepository(
    private val pointHistories: MutableList<PointHistory>,
) : PointHistoryRepository {
    override fun findHistoriesByUserId(userId: Long): List<PointHistory> = pointHistories.filter { it.userId == userId }

    override fun save(pointHistory: PointHistory): PointHistory {
        pointHistories.add(pointHistory)
        return pointHistory
    }
}
