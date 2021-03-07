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
package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

private enum class BoxState6 { Collapsed, Expanded }

@Composable
private fun AnimatingBox(boxState: BoxState6) {
    val transitionData = updateTransitionData(boxState)
    // UI tree
    Box(
        modifier = Modifier
            .background(transitionData.color)
            .size(transitionData.size)
    )
}

// Holds the animation values.
private class TransitionData6(
    color: State<Color>,
    size: State<Dp>
) {
    val color by color
    val size by size
}

// Create a Transition and return its animation values.
@Composable
private fun updateTransitionData(boxState: BoxState6): TransitionData6 {
    val transition = updateTransition(boxState)
    val color = transition.animateColor { state ->
        when (state) {
            BoxState6.Collapsed -> Color.Gray
            BoxState6.Expanded -> Color.Red
        }
    }
    val size = transition.animateDp { state ->
        when (state) {
            BoxState6.Collapsed -> 64.dp
            BoxState6.Expanded -> 128.dp
        }
    }
    return remember(transition) { TransitionData6(color, size) }
}

@ExperimentalAnimationApi
@Composable
fun DemoScreen6() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        var currentState by remember { mutableStateOf(BoxState6.Collapsed) }

        Text(
            "ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {

                    if (currentState == BoxState6.Expanded) {
                        currentState = BoxState6.Collapsed
                    } else {
                        currentState = BoxState6.Expanded
                    }
                }
                .width(200.dp)
                .height(50.dp)
        )

        AnimatingBox(boxState = currentState)
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview6() {
    MyTheme {
        DemoScreen6()
    }
}
