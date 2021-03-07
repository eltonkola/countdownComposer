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

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

// TODO - donet work
@ExperimentalAnimationApi
@Composable
fun DemoScreen8() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        var visible by remember { mutableStateOf(true) }

        Text(
            "ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    visible = !visible
                }
                .width(200.dp)
                .height(50.dp)
        )

        val coroutineScope = rememberCoroutineScope()

        val anim = remember {
            TargetBasedAnimation(
                animationSpec = tween(1000),
                typeConverter = Float.VectorConverter,
                initialValue = 200f,
                targetValue = 1000f
            )
        }
        var playTime by remember { mutableStateOf(0L) }
        var inaAnimation = true
        coroutineScope.launch {
            val startTime = withFrameNanos { it }

            do {
                playTime = withFrameNanos { it } - startTime
                val animationValue = anim.getValueFromNanos(playTime)
                Log.v("eltonkolaxxxx", "animationValue: $animationValue")
            } while (inaAnimation)
        }

        Box(
            Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.Green)
        )
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview8() {
    MyTheme {
        DemoScreen8()
    }
}
