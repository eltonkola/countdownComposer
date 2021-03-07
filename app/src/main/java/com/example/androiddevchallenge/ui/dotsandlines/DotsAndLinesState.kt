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
package com.example.androiddevchallenge.ui.dotsandlines

import androidx.compose.ui.unit.IntSize
import com.example.androiddevchallenge.ui.dotsandlines.Dot.Companion.next
import kotlin.math.roundToInt

data class DotsAndLinesState(
    val dots: List<Dot>,
    val dotRadius: Float,
    val size: IntSize,
    val speed: Float
) {
    companion object {

        fun create(
            size: IntSize,
            populationFactor: Float,
            dotRadius: Float,
            speed: Float
        ) = DotsAndLinesState(
            dots = (0..size.realPopulation(populationFactor)).map {
                Dot.create(size)
            },
            dotRadius = dotRadius,
            size = size,
            speed = speed
        )

        fun DotsAndLinesState.next(durationMillis: Long): DotsAndLinesState {
            return copy(
                dots = dots.map {
                    it.next(size, durationMillis, dotRadius, speed)
                }
            )
        }

        fun DotsAndLinesState.populationControl(populationFactor: Float): DotsAndLinesState {
            val count = size.realPopulation(populationFactor = populationFactor)

            return if (count < dots.size) {
                copy(dots = dots.shuffled().take(count))
            } else {
                copy(dots = dots + (0..count - dots.size).map { Dot.create(size) })
            }
        }

        private fun IntSize.realPopulation(populationFactor: Float): Int {
            return (width * height / 10_000 * populationFactor).roundToInt()
        }
    }
}
