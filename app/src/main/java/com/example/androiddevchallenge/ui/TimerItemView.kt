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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun TimerItemView(time: Int, title: String) {

    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row() {

//            Crossfade(time,
//                animationSpec = spring(stiffness = 50f) //snap(delayMillis = 50) // tween(durationMillis = 300),
//            ) {
//                Text(
//                    text = time.toStr(),
//                    modifier = Modifier,
//                    fontSize = 42.sp, //size.value.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White,
//                )
//            }

            val res0 = time % 10
            var res1 = time / 10
            val res2 = if (res1 > 9) {
                res1 %= 10
                time / 100
            } else 0

            if (res2 > 0) {
                TickBox(boxState = TickState.fromInt(res2))
            }
            TickBox(boxState = TickState.fromInt(res1))
            TickBox(boxState = TickState.fromInt(res0))
        }

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@ExperimentalAnimationApi
@Preview("TimerItemViewDarkPreview", widthDp = 360, heightDp = 640)
@Composable
fun TimerItemViewDarkPreview() {
    MyTheme() {
        TimerItemView(18, "Minutes")
    }
}
