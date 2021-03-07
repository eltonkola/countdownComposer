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
package com.example.androiddevchallenge.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TickState(val value: Int) {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    companion object {
        private val map = values().associateBy(TickState::value)
        fun fromInt(type: Int) = map[type] ?: ZERO
    }
}

private class TickTransitionData(
    color: State<Color>,
    size: State<Dp>,
    rotationX0: State<Float>,
    rotationX1: State<Float>,
    rotationX2: State<Float>,
    rotationX3: State<Float>,
    rotationX4: State<Float>,
    rotationX5: State<Float>,
    rotationX6: State<Float>,
    rotationX7: State<Float>,
    rotationX8: State<Float>,
    rotationX9: State<Float>,
) {
    val color by color
    val size by size
    val rotationX0 by rotationX0
    val rotationX1 by rotationX1
    val rotationX2 by rotationX2
    val rotationX3 by rotationX3
    val rotationX4 by rotationX4
    val rotationX5 by rotationX5
    val rotationX6 by rotationX6
    val rotationX7 by rotationX7
    val rotationX8 by rotationX8
    val rotationX9 by rotationX9
}

@Composable
fun SingleTickBox(number: String, rotationX: Float, visible: Boolean) {
    Box(
        Modifier
            .width(28.dp)
            .height(36.dp)
            .padding(end = 0.5.dp)
            .graphicsLayer(
                rotationX = rotationX,
                //  alpha = if(visible) 1f else 0f
            )
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.6f))
        ) {}
        Text(
            text = number,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Composable
fun TickBox(boxState: TickState) {
    val transitionData = updateTransitionData(boxState)
    Box {
        SingleTickBox("0", transitionData.rotationX0, shouldShow(0, boxState.value))
        SingleTickBox("1", transitionData.rotationX1, shouldShow(0, boxState.value))
        SingleTickBox("2", transitionData.rotationX2, shouldShow(0, boxState.value))
        SingleTickBox("3", transitionData.rotationX3, shouldShow(0, boxState.value))
        SingleTickBox("4", transitionData.rotationX4, shouldShow(0, boxState.value))
        SingleTickBox("5", transitionData.rotationX5, shouldShow(0, boxState.value))
        SingleTickBox("6", transitionData.rotationX6, shouldShow(0, boxState.value))
        SingleTickBox("7", transitionData.rotationX7, shouldShow(0, boxState.value))
        SingleTickBox("8", transitionData.rotationX8, shouldShow(0, boxState.value))
        SingleTickBox("9", transitionData.rotationX9, shouldShow(0, boxState.value))
    }
}

fun shouldShow(value: Int, current: Int): Boolean {
    return value == current + 1 || value == current - 1
}

@Composable
private fun updateTransitionData(boxState: TickState): TickTransitionData {
    val transition = updateTransition(boxState)
    val color = transition.animateColor { state ->
        when (state) {
            TickState.ONE -> Color.Black
            TickState.TWO -> Color.Blue
            TickState.THREE -> Color.Gray
            TickState.FOUR -> Color.Red
            TickState.FIVE -> Color.Green
            TickState.SIX -> Color.Magenta
            TickState.SEVEN -> Color.Cyan
            TickState.EIGHT -> Color.Yellow
            else -> Color.Red
        }
    }

    val size = transition.animateDp { state ->
        when (state) {
            TickState.ONE -> 122.dp
            else -> (boxState.value + 120).dp
        }
    }

    val transitionSpec = tween<Float>(durationMillis = 600)

    val rotationX0 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.ZERO -> 0f
            else -> -90f
        }
    }
    val rotationX1 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.ONE -> 0f
            else -> -90f
        }
    }
    val rotationX2 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.TWO -> 0f
            else -> -90f
        }
    }

    val rotationX3 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.THREE -> 0f
            else -> -90f
        }
    }
    val rotationX4 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.FOUR -> 0f
            else -> -90f
        }
    }
    val rotationX5 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.FIVE -> 0f
            else -> -90f
        }
    }
    val rotationX6 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.SIX -> 0f
            else -> -90f
        }
    }
    val rotationX7 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.SEVEN -> 0f
            else -> -90f
        }
    }
    val rotationX8 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.EIGHT -> 0f
            else -> -90f
        }
    }
    val rotationX9 = transition.animateFloat(transitionSpec = { transitionSpec }) { state ->
        when (state) {
            TickState.NINE -> 0f
            else -> -90f
        }
    }

    return remember(transition) {
        TickTransitionData(
            color,
            size,
            rotationX0,
            rotationX1,
            rotationX2,
            rotationX3,
            rotationX4,
            rotationX5,
            rotationX6,
            rotationX7,
            rotationX8,
            rotationX9
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun TickView() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        var currentNr by remember { mutableStateOf(0) }

        Text(
            "Number: $currentNr",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    if (currentNr < 9) {
                        currentNr++
                    } else {
                        currentNr = 0
                    }
                }
                .width(200.dp)
                .height(50.dp)
        )

        TickBox(boxState = TickState.fromInt(currentNr))
    }
}
