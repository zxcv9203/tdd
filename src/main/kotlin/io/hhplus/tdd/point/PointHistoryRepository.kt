package io.hhplus.tdd.point

interface PointHistoryRepository {
    fun findHistoriesByUserId(userId: Long): List<PointHistory>

    fun save(pointHistory: PointHistory): PointHistory
}
