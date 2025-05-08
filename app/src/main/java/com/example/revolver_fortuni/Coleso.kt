package com.example.revolver_fortuni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.revolver_fortuni.ui.theme.Revolver_fortuniTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class Coleso : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Revolver_fortuniTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    revolver()
                }
            }
        }
    }
}

@Composable
fun revolver() {
    val scope = rememberCoroutineScope()

    var isRotating by remember { mutableStateOf(false) }
    var isDecelerating by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }
    var rotationDurationSec by remember { mutableStateOf(0.3f) }
    val initialSpeed = 360f / (rotationDurationSec * 1000f)
    var currentSpeed by remember { mutableStateOf(0f) }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .rotate(rotation.value)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (!isRotating) {
                        isDecelerating = false
                        isRotating = true
                    }
                },
                enabled = !isRotating && !isDecelerating
            ) {
                Text("начать")
            }

            Button(
                onClick = {
                    if (isRotating && !isDecelerating) {
                        isDecelerating = true
                    }
                },
                enabled = isRotating && !isDecelerating
            ) {
                Text("стоп")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


    }
}
