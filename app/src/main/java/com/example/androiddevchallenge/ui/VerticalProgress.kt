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
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun VerticalProgress(
    modifier: Modifier,
    max: Int,
    vlera: Int,
    color: Color = "#C1D4C3".hexToColor()
) {
    Column(modifier = modifier) {
        (0..max).reversed().forEach { place ->
            val alpha: Float by animateFloatAsState(
                targetValue = if (vlera > place) {
                    // 0.6f
                    (60 - place).toFloat() / 100 + 0.2f
                } else 0.0f,
                animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    // .weight(1f)
                    .padding(0.8.dp)
                    .alpha(alpha),
                shape = RoundedCornerShape(1.dp),
                backgroundColor = color // secColors[place],
                //  elevation = 1.dp
            ) {}
        }
    }
}

@ExperimentalAnimationApi
@Preview("CountDownScreenPreview", widthDp = 360, heightDp = 640)
@Composable
fun CountDownScreenPreview() {
    MyTheme {
        VerticalProgress(Modifier, 60, 20, Color.Red)
    }
}
