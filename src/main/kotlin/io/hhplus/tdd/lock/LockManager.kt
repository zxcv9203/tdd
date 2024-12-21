package io.hhplus.tdd.lock

interface LockManager {
    fun <T> withLock(
        key: Long,
        action: () -> T,
    ): T
}
