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

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize

data class Dot(
    val position: Offset,
    val vector: Offset
) {
    companion object {
        /**
         * Calculate this [Dot]'s distance to another one.
         */
        infix fun Dot.distanceTo(another: Dot): Float {
            return (position - another.position).getDistance()
        }

        /**
         * Calculate where this dot will be in the next iteration.
         *
         * @param borders Size of the canvas where dots bounce.
         * @param durationMillis How long time is going to pass until next iteration.
         * @param dotRadius The radius of this dot when it is drawn.
         * @param speedCoefficient Although there is vector that indicates motion, this
         * parameter is used to speed up or down the animation at will.
         */
        fun Dot.next(
            borders: IntSize,
            durationMillis: Long,
            dotRadius: Float,
            speedCoefficient: Float
        ): Dot {
            val speed = vector * speedCoefficient
            return Dot(
                position = position + Offset(
                    x = speed.x / 1000f * durationMillis,
                    y = speed.y / 1000f * durationMillis,
                ),
                vector = vector
            ).let { (position, vector) ->
                val borderBottom = borders.height - dotRadius
                val borderRight = borders.width - dotRadius
                Dot(
                    position = Offset(
                        x = when {
                            position.x < dotRadius -> dotRadius - (position.x - dotRadius)
                            position.x > borderRight -> borderRight - (position.x - borderRight)
                            else -> position.x
                        },
                        y = when {
                            position.y < dotRadius -> dotRadius - (position.y - dotRadius)
                            position.y > borderBottom -> borderBottom - (position.y - borderBottom)
                            else -> position.y
                        }
                    ),
                    vector = Offset(
                        x = when {
                            position.x < dotRadius -> -vector.x
                            position.x > borderRight -> -vector.x
                            else -> vector.x
                        },
                        y = when {
                            position.y < dotRadius -> -vector.y
                            position.y > borderBottom -> -vector.y
                            else -> vector.y
                        }
                    )
                )
            }
        }

        /**
         * Create a random dot instance belonging to @param borders.
         */
        fun create(borders: IntSize): Dot {
            return Dot(
                position = Offset(
                    (0..borders.width).random().toFloat(),
                    (0..borders.height).random().toFloat()
                ),
                vector = Offset(
                    // First, randomize direction. Second, randomize amplitude of speed vector.
                    listOf(-1, 1).random() * (borders.width / 50..borders.width / 20).random()
                        .toFloat(),
                    listOf(-1, 1).random() * (borders.height / 50..borders.height / 20).random()
                        .toFloat()
                )
            )
        }
    }
}
