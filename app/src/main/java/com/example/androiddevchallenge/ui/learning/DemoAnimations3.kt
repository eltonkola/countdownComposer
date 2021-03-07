package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
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
fun DemoScreen3() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {


        //  var showHide by mutableStateOf(false)


        var show by remember { mutableStateOf(true) }


        Text("ShowHide",
            Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .clickable {
                    show = !show

                }
                .width(200.dp)
                .height(50.dp))


        val alpha: Float by animateFloatAsState(if (show) 1f else 0.1f)
        val rotationX: Float by animateFloatAsState(if (show) 0f else 45f)
        val scale: Float by animateFloatAsState(if (show) 2f else 1f)



        Box(
            Modifier
                .width(100.dp)
                .height(100.dp)
                .graphicsLayer(
                    alpha = alpha,
                    rotationX = rotationX,
                    //   rotationY = rotationY,
//                    scaleX = scale,
//
                )
                .background(Color.Red)
        ) {

        }


    }
}


@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DemoScreenPreview3() {
    MyTheme {
        DemoScreen3()
    }
}