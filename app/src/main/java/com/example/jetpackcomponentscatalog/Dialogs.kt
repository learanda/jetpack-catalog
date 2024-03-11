package com.example.jetpackcomponentscatalog

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyConfirmationDialog(show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(title = "Phone ringtone")
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                var status by remember { mutableStateOf("") }
                MyRadioButtonList(name = status, onItemSelected = { status = it })
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                Row(modifier = Modifier.align(Alignment.End).padding(top = 4.dp)) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "CANCEL")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "OK")
                    }
                }
            }

        }
    }
}

@Composable
fun MyCustomDialog(show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(title = "Set backup account")
                AccountItem("ejemplo1@gmail.com", R.drawable.jc)
                AccountItem("ejemplo2@gmail.com", R.drawable.ic_launcher_background)
                AccountItem("Añadir nueva cuenta", R.drawable.add)
            }

        }
    }
}

@Composable
fun MySimpleCustomDialog(show: Boolean, onDismissRequest: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialog() {
    AlertDialog(
        onDismissRequest = { },
        modifier = Modifier,
        properties = DialogProperties(),
        content = {
            Text(
                text = "Acá en realidad se debería poner una Card"
            )
        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDialogWithCard() {
    AlertDialog(
        onDismissRequest = { },
        modifier = Modifier,
        properties = DialogProperties(),
        content = {
            Surface {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Ejemplo 1")
                    Text(text = "Ejemplo 2")
                    Text(text = "Ejemplo 3")
                }
            }
        })

}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun AlertDialogExampleLoader(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (show) {
        AlertDialogExample(
            { onDismissRequest() },
            { onConfirmation() },
            "Title",
            "Description",
            Image(
                painter = painterResource(id = R.drawable.jc),
                contentDescription = "ejemplo"
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Unit,
) {
    AlertDialog(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.jc),
                contentDescription = "ejemplo",
                //modifier = Modifier.clip(RoundedCornerShape(50f))
                modifier = Modifier
                    .clip(CircleShape)
                    .border(5.dp, Color.Blue, CircleShape)
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun MyTitleDialog(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}