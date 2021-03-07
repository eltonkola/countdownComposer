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
package com.example.androiddevchallenge

import android.content.Context
import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.data.PrefRepo
import java.text.SimpleDateFormat
import java.util.Date

data class TimeZ(val days: Int, val hours: Int, val minutes: Int, val seconds: Int)

class MainViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(PrefRepo(context)) as T
    }
}

class MainViewModel(private val repo: PrefRepo) : ViewModel() {

    private var timer: CountDownTimer
    private val sdf = SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa")

    var targetDate by mutableStateOf("")
        private set
    var remaining by mutableStateOf(TimeZ(0, 0, 0, 0))
        private set

    var showHide by mutableStateOf(false)
        private set

    var finished by mutableStateOf(false)

    var celebrate by mutableStateOf(false)

    init {

        // val date = Date(repo.getFakeTarget())
        val date = Date(repo.getTargetDate())
        targetDate = sdf.format(date)

        val today = Date()

        timer = object : CountDownTimer(date.time - today.time, 1000) {
            override fun onFinish() {
                onTimerTick(0)
            }

            override fun onTick(time: Long) {
                onTimerTick(time)
            }
        }
        timer.start()
    }

    override fun onCleared() {
        timer.cancel()
        super.onCleared()
    }

    private fun onTimerTick(time: Long) {
        finished = if (time == 0L) {
            timer.cancel()
            true
        } else {
            false
        }

        val days = time / (24 * 60 * 60 * 1000)
        val hours = time / (60 * 60 * 1000) % 24
        val minutes = time / (60 * 1000) % 60
        val seconds = time / 1000 % 60
        remaining = remaining.copy(
            days = days.toInt(),
            hours = hours.toInt(),
            minutes = minutes.toInt(),
            seconds = seconds.toInt()
        )
        showHide = seconds.toInt() % 2 == 0
    }

    fun restartTimer(time: Long) {
        repo.saveTargetDate(time)
        timer.cancel()
        val date = Date(time)

        targetDate = sdf.format(date)

        val today = Date()

        timer = object : CountDownTimer(date.time - today.time, 1000) {
            override fun onFinish() {
                onTimerTick(0)
            }

            override fun onTick(time: Long) {
                onTimerTick(time)
            }
        }
        timer.start()
    }
}
