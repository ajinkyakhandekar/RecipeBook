package com.tghc.recipebook.extention

import android.text.format.DateUtils
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

fun DatePicker.setToday() {
    maxDate = Calendar.getInstance().timeInMillis
}

fun DatePicker.getDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar.time
}

fun DatePicker.initialise(dateChanged: () -> Unit) {
    val c = Calendar.getInstance()
    val dateChangeListener = DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
        dateChanged.invoke()
    }
    init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), dateChangeListener)
}

fun String.dateInFormat(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    var parsedDate: Date? = null
    catchAll {
        parsedDate = dateFormat.parse(this)
    }
    return parsedDate
}

fun Date.dateInString(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.US)
    return sdf.format(this)
}

fun getRelativeDate(date: Date?): String {
    val niceDateStr = DateUtils.getRelativeTimeSpanString(date!!.time, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS)
    return niceDateStr.toString()
}

fun getRelativeDateShort(date: Date?): String {
    val seconds = ((Calendar.getInstance().timeInMillis - date!!.time) / 1000).toInt()

    if (seconds > 0) {
        return when {
            seconds < 60 -> "$seconds s"
            seconds < 3600 -> {
                val minute = seconds / 60
                "$minute m"
            }
            seconds < 86400 -> {
                val hour = seconds / 3600
                "$hour h"
            }
            else -> {
                val day = seconds / 86400
                "$day d"
            }
        }
    } else {
        return ""
    }
}
