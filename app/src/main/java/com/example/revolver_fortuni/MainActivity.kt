package com.example.revolver_fortuni

import android.content.Intent
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Revolver_fortuniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Oboi()
                    Box(                                       //основной бокс
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Exit { finishAffinity()}               //кнопка выхода
                        Image(
                            painter = painterResource(R.drawable.cnopkaplay),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(width = 250.dp, height = 120.dp)
                                .clickable {
                                val gameac = Intent(this@MainActivity, Game::class.java)
                                startActivity(gameac)
                            }
                        )
                        nastroiki{val intent = Intent(this@MainActivity, Settings::class.java)
                            startActivity(intent)}
                    }
                    localgameB()
                }
            }
        }
    }
}


@Composable
fun localgameB(){                    ////создание кнопки для быстрого колеса
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 52.dp, vertical = 270.dp)

    ){
        Button(

            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {

                val game2ac = Intent(context, Game2::class.java)
                context.startActivity(game2ac)
            }
        ) {
            Text(
                "быстрое колесо",
                color = Color.Black,

                )
        }
    }
}

@Composable
fun BoxScope.nastroiki(onClick: () -> Unit){                                                 //создание кнопки настройки
    IconButton(onClick = onClick,) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_settings_24), // Замените на вашу иконку
            contentDescription = "Иконка",
            modifier = Modifier.size(24.dp) // Устанавливаем размер иконки
        )
    }
}

@Composable
fun BoxScope.Exit(onClick: () -> Unit){                        //создание кнопки для выхода
    IconButton(
        modifier = Modifier.align(Alignment.BottomEnd),
        onClick = {
            onClick()
            if(globalUsersStorage.users.size!=0){

                while (globalUsersStorage.users.size!=0){
                    globalUsersStorage.users.removeAt(0)
                }
            }
        }
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
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.oboi), // заменить на имя изображения
        contentDescription = ""
    )
}

