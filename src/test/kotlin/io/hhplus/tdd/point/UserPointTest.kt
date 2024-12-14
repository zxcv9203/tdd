package io.hhplus.tdd.point

import io.hhplus.tdd.point.stub.UserPointFixture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UserPointTest {
    @Nested
    @DisplayName("포인트 충전")
    inner class Charge {

        @Test
        @DisplayName("[성공] 포인트 충전에 성공한다.")
        fun success() {
            val userPoint = UserPointFixture.create(1L, 100L)
            val want = UserPointFixture.create(1L, 200L)

            val got = userPoint.charge(100L)

            assertThat(got).isEqualTo(want)
        }

        @ParameterizedTest
        @ValueSource(longs = [0, -1])
        @DisplayName("[실패] 충전 포인트가 0이나 음수 경우 예외를 발생한다.")
        fun failWhenChargeNegativePoint(amount: Long) {
            val userPoint = UserPointFixture.create(1L, 100L)

            assertThatThrownBy { userPoint.charge(amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(PointErrorMessage.CHARGE_AMOUNT_SHOULD_BE_POSITIVE.message)
        }

        @ParameterizedTest
        @ValueSource(longs = [1_999_901, 2_000_000])
        @DisplayName("[실패] 최대 포인트를 초과하는 경우 예외를 발생한다.")
        fun failWhenExceedMaxPoint(amount: Long) {
            val userPoint = UserPointFixture.create(1L, 100L)

            assertThatThrownBy { userPoint.charge(amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(PointErrorMessage.EXCEED_MAX_POINT.message)
        }
    }
}