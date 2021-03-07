package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme


@ExperimentalAnimationApi
@Composable
fun DemoScreen9() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        var visible by remember { mutableStateOf(true) }

        Text("ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    visible = !visible
                }
                .width(200.dp)
                .height(50.dp))


//        val alpha: Float by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            // configure the animation duration and easing
//            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
//        )

//        val alpha by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            animationSpec = spring(
//                dampingRatio = Spring.DampingRatioHighBouncy,
//                stiffness = Spring.StiffnessMedium
//            )
//        )

//        val alpha by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            animationSpec = keyframes {
//                durationMillis = 375
//                0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
//                0.2f at 15 with FastOutLinearInEasing // for 15-75 ms
//                0.4f at 75 // ms
//                0.4f at 225 // ms
//            }
//        )

//        val alpha by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            animationSpec = repeatable(
//                iterations = 3,
//                animation = tween(durationMillis = 300),
//                repeatMode = RepeatMode.Reverse
//            )
//        )

//        val alpha by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            animationSpec = infiniteRepeatable(
//                animation = tween(durationMillis = 300),
//                repeatMode = RepeatMode.Reverse
//            )
//        )

//        val alpha by animateFloatAsState(
//            targetValue = if (visible) 1f else 0.5f,
//            animationSpec = snap(delayMillis = 50)
//        )

//        val color: Color by animateColorAsState(
//            targetValue = if (visible) Color.Red else Color.Black,
//            animationSpec =  snap(delayMillis = 50)
//        )


        val CustomEasing = Easing { fraction -> fraction + (fraction / 2) }

        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0.1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = CustomEasing
            )
        )

        Box(
            Modifier
                .width(100.dp)
                .height(100.dp)
                .graphicsLayer(alpha = alpha)
                .background(Color.Red)

        )


    }
}


@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview9() {
    MyTheme {
        DemoScreen9()
    }
}
