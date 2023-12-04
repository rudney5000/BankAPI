package com.dedytech.bankapi.enums

enum class CardRoundingStep(
    private val roundingStep: Int
) {
    STEP10(10),
    STEP50(50),
    STEP100(100),
    STEP1000(1000);
}