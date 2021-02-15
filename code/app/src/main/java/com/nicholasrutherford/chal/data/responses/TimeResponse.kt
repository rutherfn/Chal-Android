package com.nicholasrutherford.chal.data.responses

data class TimeResponse(
    var date: Int = 0,
    var hours: Int = 0,
    var seconds: Int = 0,
    var month: Int = 0,
    var timeZoneOffSet: Int = 0 ,
    var year: Int = 0,
    var minutes: Int = 0,
    var time: Double = 0.0,
    var day: Int = 0
)
