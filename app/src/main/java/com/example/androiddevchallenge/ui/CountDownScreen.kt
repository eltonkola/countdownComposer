package com.example.androiddevchallenge.ui

import androidx.compose.animation.Animatable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eltonkola.komposekonfetti.KonfettiKompose
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PrefRepo
import com.example.androiddevchallenge.ui.dotsandlines.AnimatedBackground
import com.example.androiddevchallenge.ui.theme.MyTheme
import nl.dionsegijn.konfetti.ParticleSystem
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.SizeZ


val secColors = listOf(
    "#bc0020", "#f91f86", "#f60d07", "#a40052",
    "#f32f18", "#bc1a65", "#ff3123", "#e84c89",
    "#eb2c16", "#fe4b93", "#e00012", "#f85e95",
    "#e13019", "#d20069", "#ff4432", "#ff3786",
    "#860300", "#ed0066", "#9e1106", "#ff597a",
    "#af1407", "#e95673", "#cb0012", "#d54268",
    "#f1002e", "#ab0041", "#ff5d45", "#c60052",
    "#dd452a", "#d90057", "#dd6045", "#fb0058",
    "#921912", "#ff0046", "#a51a2e", "#ff153e",
    "#b82844", "#ff4e41", "#ed0058", "#b6331d",
    "#ff1551", "#b53332", "#f76376", "#c12915",
    "#fd5e70", "#af001a", "#f16465", "#d10026",
    "#f36a58", "#b90031", "#e35a3e", "#e3003e",
    "#d95035", "#ff5064", "#c83d25", "#da4d58",
    "#d2002c", "#c73e46", "#ff5d51", "#ff5359"
).map { it.hexToColor() }


//val colorz = listOf("#791650"    ,"#841B58"    ,"#8F1F60"
//    ,"#9A2467"    ,"#A5286F"    ,"#B02D77"    ,"#BC317F"
//    ,"#C73687"    ,"#D23A8F"    ,"#DD3F96"    ,"#E8439E"
//    ,"#F348A6"    ,"#F348A6"    ,"#E3429D"    ,"#D33B93"
//    ,"#C3358A"    ,"#B32F80"    ,"#A32877"    ,"#92226D"
//    ,"#821B64"    ,"#72155A"    ,"#620F51"    ,"#520847"
//    ,"#42023E"    ,"#42023E"    ,"#470440"    ,"#4C0641"
//    ,"#510743"    ,"#560945"    ,"#5B0B46"    ,"#600D48"
//    ,"#650F49"    ,"#6A114B"    ,"#6F124D"    ,"#74144E" ,"#791650")

//val secColors = mutableListOf<String>().apply{
//    addAll(colorz)
//    addAll(colorz.reversed())
//}.map { it.hexToColor() }

fun String.hexToColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}

@ExperimentalUnsignedTypes
@ExperimentalAnimationApi
@Composable
fun CountDownScreen(model: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    //we can animate all the other values if we need to
    AnimatedBackground(
        modifier = Modifier.alpha(0.6f),
        populationFactor = if (model.remaining.seconds > 30) {
            model.remaining.seconds.toFloat() / 100 + 0.2f
        } else {
            (60 - model.remaining.seconds.toFloat()) / 100 + 0.2f
        }
    )


    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val (topBar, card, bars, targetDate) = createRefs()

        Box(modifier = Modifier
            .constrainAs(targetDate) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(20.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(8.dp)
            )

        ) {
            Text(
                text = model.targetDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(6.dp)
            )
        }

        AppBarScreen(model,
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


        val color = remember { Animatable(Color.Red) }

        LaunchedEffect(model.remaining.seconds / 5) {
            color.animateTo(
                targetValue = secColors[model.remaining.seconds / 5],
                animationSpec = tween(durationMillis = 5000),
            )
        }
        val spaceBetweenBars = 32.dp
        val barWidth = 46.dp
        val barPadding = 0.dp
        Row(modifier = Modifier
            .padding(bottom = 6.dp)
            .constrainAs(bars) {
                // top.linkTo(topBar.top, margin = 10.dp)
                start.linkTo(card.start)
                end.linkTo(card.end)
                bottom.linkTo(card.top)
            }) {

            VerticalProgress(
                modifier = Modifier
                    .width(barWidth)
                    .padding(barPadding),
                max = 59,
                vlera = model.remaining.days,
                color = color.value
            )
            Box(modifier = Modifier.width(spaceBetweenBars))

            VerticalProgress(
                modifier = Modifier
                    .width(barWidth)
                    .padding(barPadding),
                max = 59,
                vlera = model.remaining.hours,
                color = color.value
            )
            Box(modifier = Modifier.width(spaceBetweenBars))

            VerticalProgress(
                modifier = Modifier
                    .width(barWidth)
                    .padding(barPadding),
                max = 59,
                vlera = model.remaining.minutes,
                color = color.value
            )
            Box(modifier = Modifier.width(spaceBetweenBars))

            VerticalProgress(
                modifier = Modifier
                    .width(barWidth)
                    .padding(barPadding),
                max = 59,
                vlera = model.remaining.seconds,
                color = color.value
            )
        }


        //val alpha: Float by animateFloatAsState(if (model.remaining.seconds % 2 == 0) 0.9f else 0.8f)

        val translationX by animateFloatAsState(
            targetValue = if (model.remaining.seconds % 2 == 0) 6f else -6f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = FastOutSlowInEasing
                ),//tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val translationY by animateFloatAsState(
            targetValue = if (model.remaining.seconds % 3 == 0) 2f else -2f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = FastOutSlowInEasing
                ),//tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )


        val translationZ by animateFloatAsState(
            targetValue = if (model.remaining.seconds % 1 == 0) 1f else -1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2000,
                    easing = FastOutSlowInEasing
                ),//tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    // top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 140.dp)
                },
            horizontalArrangement = Arrangement.Center
        ) {


            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .alpha(0.8f)
                    // .graphicsLayer(alpha = alpha)
                    .graphicsLayer(
                        rotationX = translationX,
                        // rotationY = translationY,
                        //  rotationZ = translationZ,

                    ),
                // .animateContentSize(),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = color.value,
                elevation = 10.dp

            ) {


                Row(modifier = Modifier.padding(16.dp)) {

                    TimerItemView(model.remaining.days, "DAYS")

                    TimerDivierView(
                        modifier = Modifier
                            .width(6.dp)
                    )

                    TimerItemView(model.remaining.hours, "HOURS")

                    TimerDivierView(
                        modifier = Modifier
                            .width(6.dp)
                    )

                    TimerItemView(model.remaining.minutes, "MINUTES")

                    TimerDivierView(
                        modifier = Modifier
                            .width(6.dp)
                    )

                    TimerItemView(model.remaining.seconds, "SECONDS")


                }
            }


        }

    }

    var confettiConfig by remember { mutableStateOf(ParticleSystem()) }

    if(model.celebrate){
        confettiConfig = ParticleSystem()
            .addColors(listOf(Color.Yellow, Color.Green, Color.Magenta))
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 2f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(SizeZ(22.dp))
            .setPosition(-50f, 1000 + 50f, -50f, -50f)
            .streamFor(200, 5000L)
        model.celebrate = false
    }

    KonfettiKompose(
        modifier = Modifier.fillMaxSize(),
        config = confettiConfig
    )

}


@ExperimentalAnimationApi
@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        CountDownScreen(MainViewModel(PrefRepo(LocalContext.current)))
    }
}

