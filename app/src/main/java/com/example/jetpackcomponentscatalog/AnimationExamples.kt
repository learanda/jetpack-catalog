package com.example.jetpackcomponentscatalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSample() {
    Column() {
        var firstColor by rememberSaveable {
            mutableStateOf(false)
        }
        var realColor = if (firstColor) Color.Red else Color.Yellow
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor })

        Spacer(modifier = Modifier.size(200.dp))

        var secondColor by rememberSaveable {
            mutableStateOf(false)
        }
        val realColor2 by animateColorAsState(
            targetValue = if (secondColor) Color.Red else Color.Yellow,
            label = ""
        )
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor2)
            .clickable { secondColor = !secondColor })
    }
}

@Composable
fun ColorAnimationSample2() {
    Column() {
        var firstColor by rememberSaveable {
            mutableStateOf(false)
        }
        var showBox by rememberSaveable {
            mutableStateOf(true)
        }
        val realColor by animateColorAsState(
            targetValue = if (firstColor) Color.Red else Color.Yellow,
            label = "",
            animationSpec = tween(durationMillis = 2000),
            finishedListener = {
                showBox = false
                if (!showBox) {
                    android.os.Handler().postDelayed({
                        showBox = true
                    }, 1000)
                }
            }
        )
        if (showBox) {
            Box(modifier = Modifier
                .size(100.dp)
                .background(realColor)
                .clickable { firstColor = !firstColor })
        }
    }
}

@Composable
fun SizeAnimation() {
    Column {
        var smallSize by rememberSaveable {
            mutableStateOf(true)
        }

        val size by animateDpAsState(targetValue = if (smallSize) 50.dp else 100.dp, label = "")

        Box(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable { smallSize = !smallSize })
    }
}

@Composable
fun SizeAnimation2() {

    var showBox2 by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        var smallSize by rememberSaveable {
            mutableStateOf(true)
        }

        val size by animateDpAsState(
            targetValue = if (smallSize) 50.dp else 100.dp,
            label = "",
            animationSpec = tween(durationMillis = 2000),
            finishedListener = { showBox2 = true }
        )

        Box(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable { smallSize = !smallSize }) {
            Text(text = "1", modifier = Modifier.align(Alignment.Center))
        }

        if (showBox2) {
            Box(modifier = Modifier
                .size(size)
                .background(Color.Yellow)
                .clickable { smallSize = !smallSize }) {
                Text(text = "2", modifier = Modifier.align(Alignment.Center), color = Color.Black)
            }
        }
    }
}

@Composable
fun VisibilityAnimation() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isVisible by remember { mutableStateOf(true) }

        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Show/Hide")
        }

        Spacer(modifier = Modifier.size(50.dp))

        //AnimatedVisibility (isVisible) {
        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossfadeExampleAnimation() {
    var myComponentType: ComponentType by remember {
        mutableStateOf(ComponentType.Text)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Button(onClick = { myComponentType = getComponentTypeRadom() }) {
            Text(text = "Cambiar componente")
        }

        Crossfade(targetState = myComponentType, label = "", animationSpec = tween(durationMillis = 2000)) {
            when (it) {
                ComponentType.Image -> Icon(Icons.Default.Send, contentDescription = "send")
                ComponentType.Text -> Text(text = "Componente Text")
                ComponentType.Box -> Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Red)
                )

                ComponentType.Error -> Text(text = "¡ERROR!")
            }
        }
    }
}

fun getComponentTypeRadom(): ComponentType {
    return when (nextInt(from = 0, until = 3)) {
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}

enum class ComponentType() {
    Image, Text, Box, Error
}