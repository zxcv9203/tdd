package io.hhplus.tdd.point

import io.hhplus.tdd.database.PointHistoryTable
import org.springframework.stereotype.Repository

@Repository
class InMemoryPointHistoryRepository(
    private val pointHistoryTable: PointHistoryTable,
) : PointHistoryRepository {
    override fun findHistoriesByUserId(userId: Long): List<PointHistory> = pointHistoryTable.selectAllByUserId(userId)

    override fun save(pointHistory: PointHistory): PointHistory {
        synchronized(this) {
            return pointHistoryTable.insert(
                pointHistory.userId,
                pointHistory.amount,
                pointHistory.type,
                pointHistory.timeMillis,
            )
        }
    }
}
