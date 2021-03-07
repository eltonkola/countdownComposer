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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import com.example.androiddevchallenge.ui.dotsandlines.Dot.Companion.distanceTo
import com.example.androiddevchallenge.ui.dotsandlines.DotsAndLinesState.Companion.create
import com.example.androiddevchallenge.ui.dotsandlines.DotsAndLinesState.Companion.next
import com.example.androiddevchallenge.ui.dotsandlines.DotsAndLinesState.Companion.populationControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun AnimatedBackground(
    modifier: Modifier,
    contentColor: Color = Color.White,
    threshold: Float = 0.06f,
    maxThickness: Float = 6f,
    dotRadius: Float = 6f,
    speed: Float = 0.8f,
    populationFactor: Float = 1f
) {

    val coroutineScope = rememberCoroutineScope()
    val dotsAndLinesStateHolder = remember {
        DotsAndLinesStateHolder(coroutineScope)
    }

    val (dotsAndLinesStateFlow, setDotsAndLinesState) = dotsAndLinesStateHolder

    val dotsAndLinesState by dotsAndLinesStateFlow.collectAsState()
    setDotsAndLinesState { it.copy(speed = speed, dotRadius = dotRadius) }
    setDotsAndLinesState { it.populationControl(populationFactor) }

    Canvas(
        modifier = modifier.fillMaxSize(),

        onDraw = {

            dotsAndLinesStateHolder.init(
                create(
                    size = IntSize(this.size.width.toInt(), this.size.height.toInt()),
                    populationFactor = populationFactor,
                    dotRadius = dotRadius,
                    speed = speed
                )
            )

            val dotsAndLinesState = dotsAndLinesState ?: return@Canvas

            dotsAndLinesState.dots.forEach {
                drawCircle(contentColor, radius = dotRadius, center = it.position)
            }

            val realThreshold = threshold * sqrt(size.width.pow(2) + size.height.pow(2))

            dotsAndLinesState.dots.nestedForEach { first, second ->
                val distance = first distanceTo second

                if (distance <= realThreshold) {
                    drawLine(
                        contentColor,
                        first.position,
                        second.position,
                        0.5f + (realThreshold - distance) * maxThickness / realThreshold
                    )
                }
            }
        }
    )
}

fun <T> List<T>.nestedForEach(block: (T, T) -> Unit) {
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            block(this[i], this[j])
        }
    }
}

class DotsAndLinesStateHolder(
    private val coroutineScope: CoroutineScope
) {

    private val mutationFlow = MutableSharedFlow<(DotsAndLinesState) -> DotsAndLinesState>(
        replay = 10,
        extraBufferCapacity = 100
    )

    private val mutableStateFlow = MutableStateFlow<DotsAndLinesState?>(null)
    private val state: StateFlow<DotsAndLinesState?> = mutableStateFlow

    fun init(initialState: DotsAndLinesState) {
        if (mutableStateFlow.value != null) return

        mutableStateFlow.value = initialState

        coroutineScope.launch {
            mutationFlow.collect { mutate ->
                mutableStateFlow.value = mutableStateFlow.value?.let(mutate)
            }
        }

        coroutineScope.launch {
            while (isActive) {
                val period = 1000L / 60
                delay(period)

                mutationFlow.emit { it.next(period) }
            }
        }
    }

    operator fun component1(): StateFlow<DotsAndLinesState?> {
        return state
    }

    operator fun component2(): (((DotsAndLinesState) -> DotsAndLinesState)) -> Unit {
        return { block ->
            coroutineScope.launch {
                mutationFlow.emit(block)
            }
        }
    }
}
