package io.hhplus.tdd.point.stub

import io.hhplus.tdd.point.UserPoint

object UserPointFixture {
    private val defaultPoints = mutableMapOf(
        1L to create(1L, 100L),
        2L to create(2L, 200L),
        3L to create(3L, 300L),
    )

    fun create(id: Long, point: Long): UserPoint {
        return UserPoint(id, point, 1L)
    }

    fun prepareUserPoints(userPoints: MutableMap<Long, UserPoint> = defaultPoints): MutableMap<Long, UserPoint> {
        return userPoints
    }

    fun get(id: Long): UserPoint {
        return defaultPoints[id] ?: create(id, 0)
    }

    fun getExistsId(): Long {
        return defaultPoints.keys.first()
    }
}