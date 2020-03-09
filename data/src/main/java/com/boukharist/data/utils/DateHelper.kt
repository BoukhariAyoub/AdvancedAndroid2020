package com.boukharist.data.utils

import java.util.*

class DateHelper {

    fun getAgeFrom(today: Date, birthDate: Date): Int {
        val todayCalendar = GregorianCalendar().apply { time = today }
        val birthDateCalendar = GregorianCalendar().apply { time = birthDate }

        var age = todayCalendar.get(Calendar.DAY_OF_YEAR) - birthDateCalendar.get(Calendar.DAY_OF_YEAR)

        if (todayCalendar.get(Calendar.DAY_OF_YEAR) < birthDateCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age;
    }


    fun now(): Date {
        return Calendar.getInstance().time
    }

    fun timeStampToDate(timeStamp: Long): Date {
        return Date(timeStamp)
    }

    fun dateToTimeStamp(date: Date): Long {
        return date.time
    }
}