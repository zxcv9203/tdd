package io.hhplus.tdd.helper

import java.util.concurrent.CompletableFuture

object ConcurrentTestHelper {
    fun executeTask(
        taskCount: Int,
        task: () -> Unit,
    ) {
        val asyncTasks =
            (1..taskCount).map {
                CompletableFuture.supplyAsync { task() }
            }
        CompletableFuture.allOf(*asyncTasks.toTypedArray()).join()
    }
}
