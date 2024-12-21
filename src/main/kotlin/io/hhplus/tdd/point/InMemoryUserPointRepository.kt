package io.hhplus.tdd.point

import io.hhplus.tdd.database.UserPointTable
import org.springframework.stereotype.Repository

@Repository
class InMemoryUserPointRepository(
    private val userPointTable: UserPointTable,
) : UserPointRepository {
    override fun getById(id: Long): UserPoint = userPointTable.selectById(id)

    override fun save(userPoint: UserPoint): UserPoint = userPointTable.insertOrUpdate(userPoint.id, userPoint.point)
}
