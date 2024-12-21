package io.hhplus.tdd.point.integration

import io.hhplus.tdd.helper.ConcurrentTestHelper
import io.hhplus.tdd.point.PointHistoryRepository
import io.hhplus.tdd.point.PointService
import io.hhplus.tdd.point.UserPointRepository
import io.hhplus.tdd.point.stub.UserPointFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntegrationPointServiceTest
    @Autowired
    constructor(
        private val pointService: PointService,
        private val userPointRepository: UserPointRepository,
        private val pointHistoryRepository: PointHistoryRepository,
    ) {
        @Nested
        @DisplayName("포인트 충전")
        inner class Charge {
            @Test
            @DisplayName("[성공] 여러 사용자가 동시에 포인트를 충전할때 포인트가 정상 충전 되고, 기록이 누락되지 않는다.")
            fun test() {
                val user1Id = 1L
                val user2Id = 2L
                val amount = 100L
                val taskCount = 100
                val wantPoint = amount * taskCount

                ConcurrentTestHelper.executeTask(taskCount) {
                    pointService.charge(user1Id, amount)
                    pointService.charge(user2Id, amount)
                }

                val user1Point = userPointRepository.getById(user1Id)
                val user2Point = userPointRepository.getById(user2Id)

                val user1Histories = pointHistoryRepository.findHistoriesByUserId(user1Id)
                val user2Histories = pointHistoryRepository.findHistoriesByUserId(user2Id)

                assertThat(user1Point.point).isEqualTo(wantPoint)
                assertThat(user2Point.point).isEqualTo(wantPoint)
                assertThat(user1Histories.size).isEqualTo(taskCount)
                assertThat(user2Histories.size).isEqualTo(taskCount)
            }
        }

        @Nested
        @DisplayName("포인트 사용")
        inner class Use {
            @Test
            @DisplayName("[성공] 여러 사용자가 동시에 포인트를 사용할때 포인트가 정상 사용 되고, 기록이 누락되지 않는다.")
            fun test() {
                val user1Id = 3L
                val user2Id = 4L
                val amount = 1L
                val taskCount = 100
                val wantPoint = 0L

                userPointRepository.save(UserPointFixture.create(user1Id, 100L))
                userPointRepository.save(UserPointFixture.create(user2Id, 100L))

                ConcurrentTestHelper.executeTask(taskCount) {
                    pointService.use(user1Id, amount)
                    pointService.use(user2Id, amount)
                }

                val user1Point = userPointRepository.getById(user1Id)
                val user2Point = userPointRepository.getById(user2Id)

                val user1Histories = pointHistoryRepository.findHistoriesByUserId(user1Id)
                val user2Histories = pointHistoryRepository.findHistoriesByUserId(user2Id)

                assertThat(user1Point.point).isEqualTo(wantPoint)
                assertThat(user2Point.point).isEqualTo(wantPoint)
                assertThat(user1Histories.size).isEqualTo(taskCount)
                assertThat(user2Histories.size).isEqualTo(taskCount)
            }
        }

        @Nested
        @DisplayName("포인트 충전 / 사용이 동시에 발생하는 경우")
        inner class ChargeAndUse {
            @Test
            @DisplayName("[성공] 여러 사용자가 동시에 포인트를 충전하고 사용할때 포인트가 정상적으로 변경되고, 기록이 누락되지 않는다.")
            fun test() {
                val user1Id = 5L
                val user2Id = 6L
                val amount = 1L
                val taskCount = 100
                val wantPoint = 0L

                ConcurrentTestHelper.executeTask(taskCount) {
                    pointService.charge(user1Id, amount)
                    pointService.use(user1Id, amount)
                    pointService.charge(user2Id, amount)
                    pointService.use(user2Id, amount)
                }

                val user1Point = userPointRepository.getById(user1Id)
                val user2Point = userPointRepository.getById(user2Id)

                val user1Histories = pointHistoryRepository.findHistoriesByUserId(user1Id)
                val user2Histories = pointHistoryRepository.findHistoriesByUserId(user2Id)

                assertThat(user1Point.point).isEqualTo(wantPoint)
                assertThat(user2Point.point).isEqualTo(wantPoint)
                assertThat(user1Histories.size).isEqualTo(taskCount * 2)
                assertThat(user2Histories.size).isEqualTo(taskCount * 2)
            }
        }
    }
