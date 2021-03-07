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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun PoshteView2(time: Int) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 100.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        val infiniteTransition = rememberInfiniteTransition()
//        val color by infiniteTransition.animateColor(
//            initialValue = Color.Red,
//            targetValue = Color.Green,
//            animationSpec = infiniteRepeatable(
//                animation = tween(1000, easing = LinearEasing),
//                repeatMode = RepeatMode.Reverse
//            )
//        )

        var strated by remember { mutableStateOf(true) }

        val prev = if (time == 0) {
            0
        } else {
            time - 1
        }

        Text(
            "ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    strated = !strated
                }
                .width(200.dp)
                .height(50.dp)
        )

        val rotationX1 by animateFloatAsState(
            targetValue = if (strated) 0f else -90f,
            //        animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val rotationX2 by animateFloatAsState(
            targetValue = if (strated) -90f else 0f,
            //  animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
        )

        Box(
            Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(20.dp)
        ) {

            Box(
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .graphicsLayer(
                        rotationX = rotationX2,
                        //   clip = true,
                        //  transformOrigin = TransformOrigin.Center.copy(1f, 1f)

                    )
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = time.toString(),
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .graphicsLayer(
                        rotationX = rotationX1,
                        //  clip = true,
                        //     transformOrigin = TransformOrigin.Center.copy(1f, 1f)

                    )
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = prev.toString(),
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PoshteViewPreview2() {
    MyTheme {
        PoshteView2(8)
    }
}
