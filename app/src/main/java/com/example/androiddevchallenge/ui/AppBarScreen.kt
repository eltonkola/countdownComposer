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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainActivity
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.data.PrefRepo
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.android.material.datepicker.MaterialDatePicker

@ExperimentalAnimationApi
@Composable
fun AppBarScreen(model: MainViewModel, modifier: Modifier = Modifier) {

    val openDialog = remember { mutableStateOf(false) }
    val isPicking = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(top = 52.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "COUNTDOWN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1F),
            color = Color.White
        )
        Icon(
            imageVector = Icons.Default.Celebration,
            contentDescription = "Celebrate",
            tint = Color.White,
            modifier = Modifier
                .clickable {
                    model.celebrate = true
                }
                .padding(end = 12.dp)
        )
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier.clickable {
                openDialog.value = true
            }
        )
    }

    if (openDialog.value && !isPicking.value) {

        val activity = LocalContext.current as MainActivity
        val picker = MaterialDatePicker.Builder.datePicker().build()
        isPicking.value = true
        picker.show(activity.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            model.restartTimer(it)
            openDialog.value = false
            isPicking.value = false
        }
    }
    if (model.finished) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "The time has come")
            },
            text = {
                Text("You patience paid out, the time has come for you know what!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        model.finished = false
                        model.celebrate = true
                    }
                ) {
                    Text("Close and celebrate")
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog.value = true
                        model.finished = false
                    }
                ) {
                    Text("Set another timer")
                }
            }
        )
    }
}

@ExperimentalAnimationApi
@Preview("AppBarScreenPreview", widthDp = 360, heightDp = 640)
@Composable
fun AppBarScreenPreview() {
    MyTheme() {
        AppBarScreen(MainViewModel(PrefRepo(LocalContext.current)))
    }
}
