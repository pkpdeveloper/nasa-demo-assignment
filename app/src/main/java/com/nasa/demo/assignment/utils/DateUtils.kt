package com.nasa.demo.assignment.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getToday(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return df.format(c)
    }

    fun getDateString(year: Int, month: Int, day: Int): String {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return df.format(c.time)
    }

    fun formatDate(date: String?): CharSequence? {
        date?.let {
            val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val displayf = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val date = df.parse(it)
            return displayf.format(date)
        }
        return null
    }
}