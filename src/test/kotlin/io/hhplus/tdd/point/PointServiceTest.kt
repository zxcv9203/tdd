package io.hhplus.tdd.point

import io.hhplus.tdd.lock.LockManager
import io.hhplus.tdd.lock.fake.FakeLockManager
import io.hhplus.tdd.point.fake.FakePointHistoryRepository
import io.hhplus.tdd.point.fake.FakeUserPointRepository
import io.hhplus.tdd.point.stub.PointHistoryFixture
import io.hhplus.tdd.point.stub.UserPointFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PointServiceTest {
    private val userPointRepository: UserPointRepository = FakeUserPointRepository(UserPointFixture.prepareUserPoints())
    private val pointHistoryRepository: PointHistoryRepository =
        FakePointHistoryRepository(PointHistoryFixture.preparePointHistories())
    private val lockManager: LockManager = FakeLockManager()
    private val pointService: PointService =
        PointService(
            userPointRepository = userPointRepository,
            pointHistoryRepository = pointHistoryRepository,
            lockManager = lockManager,
        )

    @BeforeEach
    fun setUp() {
        userPointRepository.save(UserPointFixture.create(UserPointFixture.getExistsId(), 100L))
    }

    @Nested
    @DisplayName("포인트 내역 조회")
    inner class GetByUserId {
        @Test
        @DisplayName("[성공] 전달한 id가 있는 경우 해당 id의 포인트를 반환한다.")
        fun test() {
            val want = UserPointFixture.get(UserPointFixture.getExistsId())

            val got = pointService.getById(UserPointFixture.getExistsId())

            assertThat(got).isEqualTo(want)
        }
    }

    @Nested
    @DisplayName("포인트 히스토리 조회")
    inner class FindHistoriesByUserId {
        @Test
        @DisplayName("[성공] 전달한 userId에 해당하는 포인트 내역을 반환한다.")
        fun test() {
            val want = PointHistoryFixture.filterByUserId(PointHistoryFixture.getExistsUserId())

            val got = pointService.findHistoriesByUserId(PointHistoryFixture.getExistsUserId())

            assertThat(got).isEqualTo(want)
        }
    }

    @Nested
    @DisplayName("포인트 충전")
    inner class Charge {
        @Test
        @DisplayName("[성공] 포인트 충전에 성공한다.")
        fun success() {
            val want = UserPointFixture.create(UserPointFixture.getExistsId(), 200L)

            val got = pointService.charge(UserPointFixture.getExistsId(), 100L)

            assertThat(got).isEqualTo(want)
        }
    }

    @Nested
    @DisplayName("포인트 사용")
    inner class Use {
        @Test
        @DisplayName("[성공] 포인트 사용에 성공한다.")
        fun success() {
            val want = UserPointFixture.create(UserPointFixture.getExistsId(), 50L)

            val got = pointService.use(UserPointFixture.getExistsId(), 50L)

            assertThat(got).isEqualTo(want)
        }
    }
}
