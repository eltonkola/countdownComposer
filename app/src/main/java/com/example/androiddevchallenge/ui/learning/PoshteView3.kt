package com.example.androiddevchallenge.ui.learning

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun PoshteView3(time: Int) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {


        val infiniteTransition = rememberInfiniteTransition()

        val prev = if (time == 0) {
            0
        } else {
            time - 1
        }

        val rotationX1 by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -90f,
            //        animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            animationSpec = infiniteRepeatable(
                animation = tween(900, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
//
//        val rotationX2 by infiniteTransition.animateFloat(
//            initialValue = -90f,
//            targetValue =  0f,
//          //  animationSpec = tween(durationMillis = 800, easing = LinearEasing)
//            animationSpec = infiniteRepeatable(
//                animation = tween(900, easing = LinearEasing),
//                repeatMode = RepeatMode.Reverse
//            ),
//        )


        Box(
            Modifier

        ) {


//            Box(
//                Modifier
//                    .width(40.dp)
//                    .height(40.dp)
//                    .graphicsLayer(
//                       rotationX = rotationX2,
//                     //   clip = true,
//                      //  transformOrigin = TransformOrigin.Center.copy(1f, 1f)
//
//
//                    )
//                    .background(Color.Green),
//                contentAlignment = Alignment.Center
//            ){
//
//                Text(
//                    text = time.toString(),
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White,
//                    textAlign = TextAlign.Center,
//                )
//            }
            Box(
                Modifier
                    .width(40.dp)
                    .height(40.dp)
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
                    fontSize = 22.sp,
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
fun PoshteViewPreview3() {
    MyTheme {
        PoshteView2(8)
    }
}
