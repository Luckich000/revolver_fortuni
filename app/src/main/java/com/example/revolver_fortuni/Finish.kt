package com.example.revolver_fortuni

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.revolver_fortuni.ui.theme.Revolver_fortuniTheme

class Finish : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Revolver_fortuniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Exit { finishAffinity()}
                        Text(
                            modifier = Modifier
                                .absoluteOffset(x=0.dp,y=-100.dp)
                                .align(Alignment.Center),
                            text = "ВЫЙГРАЛ:",
                            fontSize = 50.sp
                        )
                        Text(
                            modifier = Modifier
                                .absoluteOffset(x=0.dp, y = -300.dp)
                                .align(Alignment.BottomCenter),
                            text = "${globalUsersStorage.users[0]}",
                            fontSize = 70.sp
                        )
                        Button(
                            modifier = Modifier.align(Alignment.BottomStart),
                            onClick = {
                                val startac = Intent(this@Finish, MainActivity::class.java)
                                startActivity(startac)
                            }
                        ) {
                            Text("Сыграть ещё раз")
                        }
                    }
                }
            }
        }
    }
}

