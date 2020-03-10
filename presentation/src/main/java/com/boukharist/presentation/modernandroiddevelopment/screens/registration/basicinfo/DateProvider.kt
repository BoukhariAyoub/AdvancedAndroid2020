package com.boukharist.presentation.modernandroiddevelopment.screens.registration.basicinfo

import java.text.SimpleDateFormat
import java.util.*

//TODO : Move to utils
interface DateProvider {

    fun dateToString(date: Date?): String?

    fun stringToDate(dateString: String?): Date?
}

class DateProviderImpl : DateProvider {

    companion object {
        const val DATE_FORMAT_PATTERN = "dd/MM/yyyy"
    }


    override fun dateToString(date: Date?): String? {
        val formatter = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.FRANCE)
        return date?.let {  formatter.format(it) }
    }

    override fun stringToDate(dateString: String?): Date? {
        val formatter = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.FRANCE)
        return dateString?.let { formatter.parse(it) }
    }

}
