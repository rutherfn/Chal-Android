package com.nicholasrutherford.chal

import java.time.LocalTime
import java.util.*

class ChallengeCalenderDay {

    val currentTime = Calendar.getInstance()
    val timeToMatch = Calendar.getInstance()

    fun dayInChallenge(): Int {
        val calendar = Calendar.getInstance()

        return when (calendar[Calendar.DAY_OF_WEEK]) {
            Calendar.MONDAY -> { 1 }
            Calendar.TUESDAY -> { 2 }
            Calendar.WEDNESDAY -> { 3 }
            Calendar.THURSDAY -> { 4 }
            Calendar.FRIDAY -> { 5 }
            Calendar.SATURDAY -> { 6 }
            else -> { 7 }
        }
    }

    fun checkIfWeShouldIncrementDayOfChallenge() {

    }

    fun firstDayInChallenge(): Int = 0

    fun lastDayInChallenge(): Int = 7


}