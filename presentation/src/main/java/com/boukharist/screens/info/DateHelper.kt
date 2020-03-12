package com.boukharist.screens.info

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


interface DateHelper {

    fun computeAge(date: Date): Int

    fun now(): Date
    fun getDateFromString(birthDate: String): Date
}

class DateHelperImpl : DateHelper {

    private fun getDiffYears(first: Date, last: Date): Int {
        val a = getCalendar(first)
        val b = getCalendar(last)
        var diff = b[YEAR] - a[YEAR]
        if (a[MONTH] > b[MONTH] ||
            a[MONTH] == b[MONTH] && a[DATE] > b[DATE]
        ) {
            diff--
        }
        return diff
    }

    private fun getCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance(Locale.FRENCH)
        cal.time = date
        return cal
    }

    override fun computeAge(date: Date): Int {
        return getDiffYears(date, now())
    }

    override fun now(): Date {
        return Date()
    }

    override fun getDateFromString(birthDate: String): Date {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
        return format.parse(birthDate)
    }
}
