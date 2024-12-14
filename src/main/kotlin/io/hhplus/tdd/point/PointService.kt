package io.hhplus.tdd.point

import org.springframework.stereotype.Service

@Service
class PointService(
    private val userPointRepository: UserPointRepository,
    private val pointHistoryRepository: PointHistoryRepository
) {
    fun getById(id: Long): UserPoint = userPointRepository.getById(id)
    fun findHistoriesByUserId(userId: Long): List<PointHistory> = pointHistoryRepository.findHistoriesByUserId(userId)
}