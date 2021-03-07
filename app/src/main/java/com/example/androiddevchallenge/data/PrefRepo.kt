/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import android.content.Context
import android.content.SharedPreferences.Editor
import java.util.Calendar
import java.util.GregorianCalendar

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
