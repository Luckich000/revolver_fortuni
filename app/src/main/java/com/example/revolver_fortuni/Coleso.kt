package com.example.revolver_fortuni

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.revolver_fortuni.ui.theme.Revolver_fortuniTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Coleso : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Revolver_fortuniTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenSwitcher()
                }
            }
        }
    }
}
var randomIndex = 0
@Composable
fun ScreenSwitcher() {
    var currentScreen by remember { mutableStateOf("screen1") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentScreen) {
            "screen1" -> Screen1 { currentScreen = "screen2" }
            "screen2" -> Screen2 { currentScreen = "screen3" }
            "screen3" -> Screen3 { currentScreen = "screen1" }
        }
    }
}
@Composable
fun Screen1(onNext: () -> Unit){
    randomIndex = Random.nextInt(globalUsersStorage.users.size)
    var isRotating by remember { mutableStateOf(true) }
    var isDecelerating by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    var rotationDurationSec by remember { mutableStateOf(0.3f) }
    val initialSpeed = 360f / (rotationDurationSec * 1000f)
    var currentSpeed by remember { mutableStateOf(0f) }
    var qwe by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.asdfgh),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )


        LaunchedEffect(isRotating, isDecelerating, rotationDurationSec) {
            if (isRotating && !isDecelerating) {
                currentSpeed = initialSpeed
                while (isRotating && !isDecelerating) {
                    val frameDurationMillis = 16L
                    rotation.snapTo((rotation.value + currentSpeed * frameDurationMillis) % 360f)
                    delay(frameDurationMillis)
                }
            } else if (isDecelerating) {
                val decelerationDurationMs = 7000L
                val steps = decelerationDurationMs / 20L
                val speedDecrement = currentSpeed / steps
                var speed = currentSpeed


                repeat(steps.toInt()) {
                    speed = max(0f, speed - speedDecrement)
                    currentSpeed = speed
                    val frameDurationMillis = 16L
                    rotation.snapTo((rotation.value + speed * frameDurationMillis) % 360f)
                    delay(frameDurationMillis)
                }
                isDecelerating = false
                isRotating = false
                currentSpeed = 0f
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.qwerty),
                contentDescription = "",
                modifier = Modifier
                    .absoluteOffset(x = 5.dp, y = 5.dp)
                    .align(Alignment.Center)
                    .size(230.dp)
                    .rotate(rotation.value)

            )
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if(qwe==1){
                    Image(painter = painterResource(R.drawable.knopkastop), contentDescription = "", modifier = Modifier
                            .absoluteOffset(x = 0.dp, y = -30.dp)
                            .size(width = 350.dp, height = 150.dp)
                            .align(Alignment.BottomCenter)
                            .clickable {
                                if (isRotating && !isDecelerating) {
                                    isDecelerating = true
                                }
                                qwe=0
                            },
                        )
                }

                if (isRotating == false) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(horizontal = 0.dp, vertical = 30.dp)
                        ) {
                            Text(
                                text = "БЫЛ ВЫБРАН:",
                                fontSize = 30.sp
                            )
                            Text(
                                text = "${globalUsersStorage.users.get(randomIndex)}",
                                fontSize = 40.sp
                            )
                        }
                        Image(painter = painterResource(R.drawable.knopkanext),
                            contentDescription = "",
                            modifier = Modifier
                                .absoluteOffset(x = 0.dp, y = -30.dp)
                                .size(width = 350.dp, height = 150.dp)
                                .align(Alignment.BottomCenter)
                                .clickable {
                                        onNext()
                                },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun Screen2(onNext: () -> Unit) {
    var offsetY by remember { mutableStateOf(0.dp) }
    var isVisible by remember { mutableStateOf(true) }

    val animatedOffsetY by animateDpAsState(targetValue = offsetY)
    val alpha by animateFloatAsState(if (isVisible) 1f else 0f)
    var next = 0
    var revolver = 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, dragAmount ->
                    if (!isVisible) return@detectVerticalDragGestures

                    if (dragAmount < 0) {
                        val newOffset = offsetY + dragAmount.dp
                        offsetY = newOffset.coerceAtLeast((-400).dp)
                        if (offsetY <= (-400).dp) {
                            isVisible = false
                        }
                    }
                    change.consume()
                }
            }
    ) {
        Box(){
            Image(
                painter = painterResource(id = R.drawable.pusto),
                contentDescription = "",
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.TopCenter),
            )
        }
        if (isVisible || alpha > 0f) {
            Image(
                painter = painterResource(id = R.drawable.potron1),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(width = 200.dp, height = 200.dp)
                    .offset(y = animatedOffsetY)
                    .alpha(alpha)
            )
            Image(
                painter = painterResource(id = R.drawable.potron2),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(width = 200.dp, height = 200.dp)
                    .offset(y = animatedOffsetY)
                    .alpha(alpha)
            )

            Image(
                painter = painterResource(id = R.drawable.potron3),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(width = 200.dp, height = 200.dp)
                    .offset(y = animatedOffsetY)
                    .alpha(alpha)
            )
        }else{
            next=1
        }
        if (next==1){
            Box(){
                Image(
                    painter = painterResource(id = R.drawable.three),
                    contentDescription = "",
                    modifier = Modifier
                        .size(400.dp)
                        .align(Alignment.TopCenter),
                )
            }
            revolver = 1

        }
        if (revolver==1){
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Image(painter = painterResource(R.drawable.knopkanext),
                    contentDescription = "",
                    modifier = Modifier
                        .absoluteOffset(x = 0.dp, y = -30.dp)
                        .size(width = 300.dp, height = 100.dp)
                        .align(Alignment.BottomCenter)
                        .clickable {
                            onNext()
                        },
                )
//                Button(
//                    modifier = Modifier
//                        .size(width = 400.dp, height = 100.dp)
//                        .align(Alignment.Center),
//                    onClick = onNext
//                ){
//                    Text(
//                        fontSize = 40.sp,
//                        text="ПРОДОЛЖИТЬ"
//                    )
//
//                }
            }
        }
    }
}

@Composable
fun Screen3 (onNext: () -> Unit){
    val scope = rememberCoroutineScope()
    var currentImage by remember { mutableStateOf(R.drawable.revolver) }
    val dimAlpha = remember { Animatable(1f) }
    val dimdAlpha = remember { Animatable(0f) }
    val brightAlpha = remember { Animatable(0f) }
    val random = Random.nextBoolean()
    var win = R.drawable.lose
    val context = LocalContext.current
    var asd by remember { mutableStateOf(false) }
    val offsetY = remember { Animatable(2500f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red=1,green=0,blue=1))
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { change, dragAmount ->

                        if (dragAmount > 10) {
                            asd=true
                            change.consume()
                            scope.launch {
                                dimdAlpha.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = 1500)
                                )
                                brightAlpha.animateTo(
                                    targetValue = 0.8f,
                                    animationSpec = tween(durationMillis = 100)
                                )
                                brightAlpha.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(durationMillis = 100)
                                )
                                dimAlpha.animateTo(0f, animationSpec = tween(durationMillis = 1500))
                                if (random){
                                    win = R.drawable.life
                                }else{
                                    win = R.drawable.lose
                                }
                                currentImage =
                                    if (currentImage == R.drawable.revolver) win else R.drawable.revolver
                                dimAlpha.snapTo(1f)
                                dimAlpha.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = 1500)
                                )
                                dimdAlpha.snapTo(0f)
                                if(!random){
                                    globalUsersStorage.users.removeAt(randomIndex)
                                    if (globalUsersStorage.users.size==1){
                                        val finishac = Intent(context, Finish::class.java)
                                        context.startActivity(finishac)
                                    }
                                }
                            }
                        }
                    }
                )
            }
    ) {
        if(asd){

            LaunchedEffect(Unit) {
                offsetY.animateTo(
                    targetValue = 1800f,
                    animationSpec = tween(durationMillis = 4000) // 4 seconds animation
                )
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(painter = painterResource(R.drawable.knopkanext),
                    contentDescription = "",
                    modifier = Modifier
                        .offset { IntOffset(100, offsetY.value.toInt()) }
                        .size(width = 300.dp, height = 100.dp)
                        .clickable {
                            onNext()
                        },
                )
            }
        }
        Image(
            painter = painterResource(id = currentImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
                .alpha(dimAlpha.value)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = dimdAlpha.value))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = brightAlpha.value))

        )


    }


}
