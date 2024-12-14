package io.hhplus.tdd.point

import org.springframework.stereotype.Service

@Service
class PointService(
    private val userPointRepository: UserPointRepository
) {
    fun getById(id: Long): UserPoint = userPointRepository.getById(id)
}