package com.example.revolver_fortuni

import android.graphics.drawable.Icon
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.revolver_fortuni.ui.theme.Revolver_fortuniTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Revolver_fortuniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    localgameB()

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        exit { finishAffinity()}
                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {}
                        ) {
                            Text(
                                "Играть",
                                color = Color.Black,
                                fontSize = 70.sp

                                )
                        }
                        nastroiki()
                    }
                }
            }
        }
    }
}


@Composable
fun localgameB(){                                      ////создание кнопки играть по локально сети
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 52.dp, vertical = 270.dp)

    ){
        Button(

            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {  }
        ) {
            Text(
                "играть по локальной сети",
                color = Color.Black,

                )
        }
    }
}

@Composable
fun nastroiki(){                                                 //создание кнопки настройки
    IconButton(onClick = { /* Действие для кнопки */ }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_settings_24), // Замените на вашу иконку
            contentDescription = "Иконка",
            modifier = Modifier.size(24.dp) // Устанавливаем размер иконки
        )
    }
}

@Composable
fun BoxScope.exit(onClick: () -> Unit){                        //создание кнопки для выхода
    IconButton(
        modifier = Modifier.align(Alignment.BottomEnd),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_exit_to_app_24), // Замените на вашу иконку
            contentDescription = "Иконка",
            modifier = Modifier.size(24.dp) // Устанавливаем размер иконки
        )
    }
}


