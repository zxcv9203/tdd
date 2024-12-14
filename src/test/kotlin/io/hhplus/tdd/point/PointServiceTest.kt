package io.hhplus.tdd.point

import io.hhplus.tdd.point.fake.FakeUserPointRepository
import io.hhplus.tdd.point.stub.UserPointFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PointServiceTest {
    private val userPointRepository: UserPointRepository = FakeUserPointRepository(UserPointFixture.prepareUserPoints())
    private val pointService: PointService = PointService(userPointRepository)

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
}