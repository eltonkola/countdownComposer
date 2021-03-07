package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun PoshteView(time: Int) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 100.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {


        val prev = if (time == 0) {
            0
        } else {
            time - 1
        }


        var visible by remember { mutableStateOf(true) }

        Text("ShowHide - $visible",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    visible = !visible
                }
                .width(200.dp)
                .height(50.dp))


        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            )
                    + expandVertically(expandFrom = Alignment.Bottom)
                    + fadeIn(initialAlpha = 0.1f),
            exit = slideOutVertically(
                //  animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            ) + shrinkVertically(
                shrinkTowards = Alignment.Bottom,
                // animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            ) + fadeOut(),

            ) {
            Text(
                prev.toString(),
                Modifier
                    .background(Color.Green)
                    .width(200.dp)
                    .height(200.dp)
            )
        }

        AnimatedVisibility(
            visible = !visible,
            enter = slideInVertically(
                initialOffsetY = { -40 }
            ) + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut(),

            ) {
            Text(
                time.toString(),
                Modifier
                    .background(Color.Red)
                    .width(200.dp)
                    .height(200.dp)
            )
        }


    }
}


@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun PoshteViewPreview() {
    MyTheme {
        PoshteView(8)
    }
}
