package io.hhplus.tdd.point.fake

import io.hhplus.tdd.point.UserPoint
import io.hhplus.tdd.point.UserPointRepository
import io.hhplus.tdd.point.stub.UserPointFixture

class FakeUserPointRepository(
    private val userPointMap: Map<Long, UserPoint>
) : UserPointRepository {
    override fun getById(id: Long): UserPoint {
        return userPointMap[id] ?: UserPointFixture.create(id, 0)
    }
}