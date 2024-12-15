package io.hhplus.tdd.point

import io.hhplus.tdd.lock.LockManager
import org.springframework.stereotype.Service

@Service
class PointService(
    private val lockManager: LockManager,
    private val userPointRepository: UserPointRepository,
    private val pointHistoryRepository: PointHistoryRepository
) {
    fun getById(id: Long): UserPoint = userPointRepository.getById(id)

    fun findHistoriesByUserId(userId: Long): List<PointHistory> = pointHistoryRepository.findHistoriesByUserId(userId)

    fun charge(id: Long, amount: Long): UserPoint =
        lockManager.withLock(id) {
            userPointRepository.getById(id)
                .charge(amount)
                .let { userPointRepository.save(it) }
        }.also { pointHistoryRepository.save(PointHistory.createByCharge(it.id, amount)) }

    fun use(id: Long, amount: Long): UserPoint =
        lockManager.withLock(id) {
            userPointRepository.getById(id)
                .use(amount)
                .let { userPointRepository.save(it) }
        }.also { pointHistoryRepository.save(PointHistory.createByUse(it.id, amount)) }

}