package com.example.androiddevchallenge.data

import android.content.Context
import android.content.SharedPreferences.Editor
import java.util.*

class PrefRepo(context: Context) {

    companion object {
        const val PREF = "dataz"
        const val TARGET_DATE = "target_date"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun getTargetDate(): Long {
        return sharedPreferences.getLong(TARGET_DATE, getDemoTarget())
    }

    fun saveTargetDate(target: Long) {
        val prefsEditor: Editor = sharedPreferences.edit()
        prefsEditor.putLong(TARGET_DATE, target)
        prefsEditor.apply()
    }

    fun getFakeTarget(): Long {
        return GregorianCalendar(2021, Calendar.MARCH, 6).time.apply {
            hours = 23
            minutes = 48
        }.time
        // return GregorianCalendar(2021, Calendar.JULY, 20).time.time
    }

    private fun getDemoTarget(): Long {
        return GregorianCalendar(2021, Calendar.JULY, 20).time.time
    }

}
