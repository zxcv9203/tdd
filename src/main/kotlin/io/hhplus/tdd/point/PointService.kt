package io.hhplus.tdd.point

import org.springframework.stereotype.Service

@Service
class PointService(
    private val userPointRepository: UserPointRepository,
    private val pointHistoryRepository: PointHistoryRepository
) {
    fun getById(id: Long): UserPoint = userPointRepository.getById(id)

    fun findHistoriesByUserId(userId: Long): List<PointHistory> = pointHistoryRepository.findHistoriesByUserId(userId)

    fun charge(id: Long, amount: Long): UserPoint =
        userPointRepository.getById(id)
            .charge(amount)
            .let { userPointRepository.save(it) }
            .also { pointHistoryRepository.save(PointHistory.createByCharge(it.id, amount)) }

    fun use(id: Long, amount: Long): UserPoint =
        userPointRepository.getById(id)
            .use(amount)
            .let { userPointRepository.save(it) }
            .also { pointHistoryRepository.save(PointHistory.createByUse(it.id, amount)) }

}