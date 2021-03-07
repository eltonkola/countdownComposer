package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
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
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun DemoScreen2() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {


        //  var showHide by mutableStateOf(false)


        var message by remember { mutableStateOf("Hello") }
        var currentPage by remember { mutableStateOf("A") }


        Text("ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    message += "A"

                    if (currentPage == "A") {
                        currentPage = "B"
                    } else {
                        currentPage = "A"
                    }
                }
                .width(200.dp)
                .height(50.dp))



        Box(
            modifier = Modifier
                .background(Color.Blue)
                .animateContentSize()
        ) {
            Text(text = message)
        }

        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        ) {
            Crossfade(targetState = currentPage) { screen ->
                when (screen) {
                    "A" -> Text("Page A")
                    "B" -> Text("Page B")
                }
            }
        }

    }
}


@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview2() {
    MyTheme {
        DemoScreen2()
    }
}
