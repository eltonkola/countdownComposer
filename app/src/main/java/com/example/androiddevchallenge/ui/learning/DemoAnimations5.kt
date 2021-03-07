package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

private enum class BoxState5 {
    Collapsed,
    Expanded
}

@ExperimentalAnimationApi
@Composable
fun DemoScreen5() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {


        //  var showHide by mutableStateOf(false)


        var show by remember { mutableStateOf(true) }


        var currentState by remember { mutableStateOf(BoxState5.Collapsed) }
        val transition = updateTransition(currentState)

        Text("ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    show = !show

                    if (currentState == BoxState5.Expanded) {
                        currentState = BoxState5.Collapsed
                    } else {
                        currentState = BoxState5.Expanded
                    }


                }
                .width(200.dp)
                .height(50.dp))


        val rect by transition.animateRect { state ->
            when (state) {
                BoxState5.Collapsed -> Rect(0f, 0f, 100f, 100f)
                BoxState5.Expanded -> Rect(100f, 100f, 300f, 300f)
            }
        }
        val borderWidth by transition.animateDp { state ->
            when (state) {
                BoxState5.Collapsed -> 5.dp
                BoxState5.Expanded -> 2.dp
            }
        }
        val borderColor by transition.animateColor { state ->
            when (state) {
                BoxState5.Collapsed -> Color.Green
                BoxState5.Expanded -> Color.Red
            }
        }

        val color by transition.animateColor(
            transitionSpec = {
                when {
                    BoxState5.Expanded isTransitioningTo BoxState5.Collapsed ->
                        spring(stiffness = 50f)
                    else ->
                        tween(durationMillis = 500)
                }
            }
        ) { state ->
            when (state) {
                BoxState5.Collapsed -> Color.Red
                BoxState5.Expanded -> Color.Green
            }
        }

        val size by transition.animateDp(
            transitionSpec = {
                when {
                    BoxState5.Expanded isTransitioningTo BoxState5.Collapsed ->
                        spring(stiffness = 250f)
                    else ->
                        spring(stiffness = 150f)
                }
            }
        ) { state ->
            when (state) {
                BoxState5.Collapsed -> 100.dp
                BoxState5.Expanded -> 200.dp
            }
        }


        Box(
            Modifier
                .width(size)
                .height(size)
                .background(color)
                .border(
                    BorderStroke(borderWidth, borderColor)
                )
        )


    }
}


@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview5() {
    MyTheme {
        DemoScreen5()
    }
}
