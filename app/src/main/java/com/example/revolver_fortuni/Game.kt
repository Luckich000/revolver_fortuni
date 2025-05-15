package com.example.revolver_fortuni


import android.content.Intent
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.activity.compose.setContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity


class Game : ComponentActivity() {
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
                        Text(
                            "ДОБАВТЕ ИГРОКОВ",
                            modifier = Modifier.align(Alignment.TopCenter),
                            fontWeight = FontWeight.Bold
                        )
                        sostavchik()
                        Text(
                            text = "список игроков:",
                            modifier = Modifier.padding(vertical = 140.dp, horizontal = 20.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun sostavchik() {
    // Состояние для хранения списка текстов
    var textList by remember { mutableStateOf(mutableListOf<String>()) }
    // Состояние для хранения текущего текста в TextField
    var currentText by remember { mutableStateOf(TextFieldValue("")) }
    var onoff by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Текстовое поле для ввода
        TextField(
            value = currentText,
            onValueChange = { newText -> currentText = newText },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            placeholder = { Text("писать здесь") }
        )


        // Кнопка для добавления текста
        Button(onClick = {
            if (currentText.text.isNotEmpty()) {
                // Добавляем текст в список
                textList.add(currentText.text)
                currentText = TextFieldValue("") // Очищаем текстовое поле
            }
        }) {

            Text("добавить игрока")
        }



        // Отображение добавленного текста


    }
    Box(
        modifier = Modifier.padding(vertical = 170.dp, horizontal = 20.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ){
            if (onoff) {
                Column {
                    textList.forEach { text ->
                        Text(
                            text = text,
                            fontSize = 35.sp,
                        )
                    }
                }
            }
            if (!onoff) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    textList.forEach { text ->
                        Text(
                            text = text,
                            fontSize = 35.sp,
                        )
                    }
                }
            }
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()

    ){

        Button(
            modifier = Modifier.align(Alignment.BottomStart),
            onClick = {
                onoff = !onoff
                if (textList.isNotEmpty()) {
                    textList.removeAt(textList.size - 1)// Удаляем последний элемент
                }
            }) {
            Text("Удалить последнего")
        }
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                showText = !showText // Переключаем видимость текста
            }) {
            Text(
                "ГОТОВО",

                )
        }


        // Отображение текста, если showText равно true
        if (showText) {
            itog(textList)
        }
    }
}

@Composable
fun itog(sostav: MutableList<String>){
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog){
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                val context = LocalContext.current
                TextButton({
                    val colesoac = Intent(context, Coleso::class.java)
                    context.startActivity(colesoac)
                }) {
                    Text(
                        text = "ДА!"
                    )
                }
            },
            dismissButton = {
                TextButton({
                    showDialog = false
                }) {
                    Text(
                        text = "нет."
                    )
                }
            },
            title = {
                Text(
                    text = "ПРОДОЛЖИТЬ?"
                )
            },
            text = {
                Text(
                    text = "вы точно хотите играть таким составом: $sostav ?"
                )
            }
        )
    }
}

