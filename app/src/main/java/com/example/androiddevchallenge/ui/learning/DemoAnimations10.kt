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
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

data class MySize(val width: Dp, val height: Dp)

// NOT CLEAR
@Composable
fun MyAnimation(targetSize: MySize) {
    val animSize: MySize by animateValueAsState<MySize, AnimationVector2D>(
        targetSize,
        TwoWayConverter(
            convertToVector = { size: MySize ->
                // Extract a float value from each of the `Dp` fields.
                AnimationVector2D(size.width.value, size.height.value)
            },
            convertFromVector = { vector: AnimationVector2D ->
                MySize(vector.v1.dp, vector.v2.dp)
            }
        )
    )

    Box(
        Modifier
            .width(animSize.width)
            .height(animSize.height)
            .background(Color.Red)

    )
}

@ExperimentalAnimationApi
@Composable
fun DemoScreen() {
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

        MyAnimation(
            MySize(200.dp, 200.dp)

        )
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview() {
    MyTheme {
        DemoScreen()
    }
}
