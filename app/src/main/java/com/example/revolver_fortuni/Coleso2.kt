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

class Coleso2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Revolver_fortuniTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Revolver()
                }
            }
        }
    }
}
var randomIndex2 = 0
@Composable
fun Revolver(){
    val context = LocalContext.current
    randomIndex2 = Random.nextInt(globalUsersStorage.users.size)
    var isRotating by remember { mutableStateOf(true) }
    var isDecelerating by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    var rotationDurationSec by remember { mutableStateOf(0.3f) }
    val initialSpeed = 360f / (rotationDurationSec * 1000f)
    var currentSpeed by remember { mutableStateOf(0f) }

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


                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .absoluteOffset(x = 0.dp, y = -30.dp)
                        .size(width = 300.dp, height = 100.dp),
                    onClick = {
                        if (isRotating && !isDecelerating) {
                            isDecelerating = true
                        }
                    },
                    enabled = isRotating && !isDecelerating
                ) {
                    Text(
                        "СТОП",
                        fontSize = 30.sp
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
                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .absoluteOffset(x = 0.dp, y = -30.dp)
                                .size(width = 300.dp, height = 100.dp),
                            onClick = {
                                val startac = Intent(context,MainActivity::class.java)
                                context.startActivity(startac)
                            }
                        ) {
                            if(globalUsersStorage.users.size!=0){
                                while (globalUsersStorage.users.size!=0){
                                    globalUsersStorage.users.removeAt(0)
                                }
                            }
                            Text(
                                "ВЫХОД",
                                fontSize = 30.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}