package io.hhplus.tdd.point

enum class PointErrorMessage(
    val message: String
) {
    CHARGE_AMOUNT_SHOULD_BE_POSITIVE("충전 금액은 0 이상이어야 합니다."),
    EXCEED_MAX_POINT("포인트 적립 최대치를 초과했습니다.")
}