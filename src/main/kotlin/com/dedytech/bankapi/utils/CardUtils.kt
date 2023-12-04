package com.dedytech.bankapi.utils

class CardUtils {

    /**
     * Method for rounding expenses
     * @param roundingStep no rounding
     * @param amount amount to round
     * @return result of rounding up
     */

    fun calculateRoundingByAmount(
        roundingStep: Int,
        amount: Float
    ): Float {
        val remainder = amount % roundingStep
        return if (remainder != 0f)
            roundingStep - remainder
        else{
            0f
        }
    }

    fun generateEncryptedPan(
        min: Int,
        maxVal: Int
    ): Int {
        var max = maxVal - min
        return (Math.random() * (++max).toDouble()).toInt() + min
    }
}