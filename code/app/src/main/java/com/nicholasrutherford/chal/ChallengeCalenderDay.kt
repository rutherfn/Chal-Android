package com.nicholasrutherford.chal

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

    fun getRealCurrentDayOnChallenge(currentDay: Int, challengeExpired: Int): Int {
        when (challengeExpired - currentDay) {
            7 -> {
                return 0
            }
            6 -> {
                return 1
            }
            5 -> {
                return 2
            }
            4 -> {
                return 3
            }
            3 -> {
                return 4
            }
            2 -> {
                return 5
            }
            1 -> {
                return 6
            }
            else -> {
                return 7
            }
        }
    }

    fun firstDayInChallenge(): Int = 0

    fun lastDayInChallenge(): Int = 7


}