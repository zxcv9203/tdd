package io.hhplus.tdd.point

import io.hhplus.tdd.database.PointHistoryTable
import org.springframework.stereotype.Repository

@Repository
class InMemoryPointHistoryRepository(
    private val pointHistoryTable: PointHistoryTable
): PointHistoryRepository {
    override fun findHistoriesByUserId(userId: Long): List<PointHistory> {
        return pointHistoryTable.selectAllByUserId(userId)
    }
}