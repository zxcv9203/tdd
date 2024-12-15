package io.hhplus.tdd.lock

import io.hhplus.tdd.helper.ConcurrentTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ReentrantLockManagerTest {
    private val lockManager: LockManager = ReentrantLockManager()

    @Nested
    @DisplayName("해당하는 Key를 ReentrantLock으로 잠금")
    inner class WithLock {
        @Test
        @DisplayName("[성공] 동시에 여러 쓰레드가 작업을 수행할 때, 잠금이 정상적으로 동작한다.")
        fun test() {
            val userId = 1L
            var got = 0L
            val want = 100000L

            ConcurrentTestHelper.executeTask(100000) {
                lockManager.withLock(userId) { got++ }
            }

            assertThat(got).isEqualTo(want)
        }
    }
}