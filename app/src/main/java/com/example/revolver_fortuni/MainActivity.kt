package com.example.revolver_fortuni

import android.graphics.drawable.Icon
import android.hardware.camera2.params.BlackLevelPattern
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Revolver_fortuniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    localgameB()                              //кнопка "играть по локальной сети"

                    Box(                                       //основной бокс
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Oboi()                                //задний фон
                        Exit { finishAffinity()}               //кнопка выхода
                        Button(
                            modifier = Modifier.align(Alignment.Center),        //оцентровка по центру
                            onClick = {/*действие*/},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(108,210,33) // Цвет фона кнопки
                            )
                        ) {
                            Text(                                 //цвет текста на кнопке
                                "Играть",                      //текст кнопки
                                color = Color.Black,               //цвет текста
                                fontSize = 70.sp                    //размер шрифта текста
                                )
                        }
                        nastroiki()                                //кнопка настроек
                    }
                }
            }
        }
    }
}


@Composable
fun localgameB(){                                      ////создание кнопки играть по локальной сети
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
    IconButton(onClick = { /* Действие*/ }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_settings_24), // замена на свою иконку
            contentDescription = "Иконка",
            modifier = Modifier.size(24.dp) // размер иконки
        )
    }
}

@Composable
fun BoxScope.Exit(onClick: () -> Unit){                        //создание кнопки для выхода
    IconButton(
        modifier = Modifier.align(Alignment.BottomEnd),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_exit_to_app_24), // замена на свою иконку
            contentDescription = "Иконка",
            modifier = Modifier.size(24.dp) // размер иконки
        )
    }
}


@Composable
fun Oboi() {                                                      //создание заднего фона
    Image(
        painter = painterResource(id = R.drawable.imba), // заменить на имя изображения
        contentDescription = ""
    )
}

