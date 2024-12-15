package io.hhplus.tdd.lock.fake

import io.hhplus.tdd.lock.LockManager

class FakeLockManager: LockManager {
    override fun <T> withLock(key: Long, action: () -> T): T {
        return action()
    }
}