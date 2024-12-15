package io.hhplus.tdd.point

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/point")
class PointController(
    private val pointService: PointService,
) {
    @GetMapping("{id}")
    fun point(
        @PathVariable id: Long,
    ): UserPoint = pointService.getById(id)

    @GetMapping("{id}/histories")
    fun history(
        @PathVariable id: Long,
    ): List<PointHistory> = pointService.findHistoriesByUserId(id)

    @PatchMapping("{id}/charge")
    fun charge(
        @PathVariable id: Long,
        @RequestBody amount: Long,
    ): UserPoint = pointService.charge(id, amount)

    @PatchMapping("{id}/use")
    fun use(
        @PathVariable id: Long,
        @RequestBody amount: Long,
    ): UserPoint = pointService.use(id, amount)
}
