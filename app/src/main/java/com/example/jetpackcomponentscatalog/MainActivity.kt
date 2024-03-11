package com.example.jetpackcomponentscatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomponentscatalog.model.Routes
import com.example.jetpackcomponentscatalog.ui.CheckInfo
import com.example.jetpackcomponentscatalog.ui.theme.JetpackComponentsCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComponentsCatalogTheme {
                var selected by remember {
                    mutableStateOf("Sheldon")
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //SuperheroStickyView()
                    //ScaffoldExample()
                    /*val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
                        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
                        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
                        composable(
                            Routes.Pantalla4.route,
                            arguments = listOf(navArgument("year") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments?.getInt("year") ?: 0
                            )
                        }
                        composable(
                            Routes.Pantalla5.route,
                            arguments = listOf(navArgument("name") { defaultValue = "Pepe" })
                        ) { backStackEntry ->
                            Screen5(
                                navigationController,
                                backStackEntry.arguments?.getString("name")
                            )
                        }
                    }*/
                    CrossfadeExampleAnimation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComponentsCatalogTheme {

        //AlertDialogExampleLoader()

        //MyDialogWithCard()

        //MinimalDialog(onDismissRequest = {})

        /*        AlertDialogExample({}, {}, "Title", "Description", Image(painter = painterResource(id = R.drawable.jc),
                    contentDescription = "ejemplo"
                ))*/
    }
}

@Composable
fun MyDropdownMenu() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var pokémons = listOf("Pikachu", "Bulbasaur", "Charmander", "Squirtle", "Pidgeotto", "Caterpie")

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            pokémons.forEach { pokémon ->
                DropdownMenuItem(text = { Text(text = pokémon, color = Color.Red) }, onClick = {
                    expanded = false
                    selectedText = pokémon
                })
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Unspecified.copy(.1f, .8f, .3f, .4f))
    ) {
        BadgedBox(
            modifier = Modifier.padding(16.dp),
            badge = {
                Badge(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ) { Text("1") }
            }) {
            Image(
                painter = painterResource(id = R.drawable.jc),
                contentDescription = "ejemplo", modifier = Modifier.clip(RoundedCornerShape(50f))
            )
        }
    }
}

@Composable
fun MyCard() {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Red
            ),
            border = BorderStroke(5.dp, Color.Red)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Ejemplo 1")
                Text(text = "Ejemplo 2")
                Text(text = "Ejemplo 3")
            }
        }

        // También existen las Surface. La diferencia con las Cards es que las Cards son Surface que
        // vienen con catacterísticas por defecto, como elevation, background shape, etc.
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Ejemplo 1")
                Text(text = "Ejemplo 2")
                Text(text = "Ejemplo 3")
            }
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = true, onClick = { }, enabled = true, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Blue,
                disabledUnselectedColor = Color.Red
            )
        )

        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Sheldon", onClick = { onItemSelected("Sheldon") })
            Text(text = "Sheldon")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Leonard", onClick = { onItemSelected("Leonard") })
            Text(text = "Leonard")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Howard", onClick = { onItemSelected("Howard") })
            Text(text = "Howard")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Rajesh", onClick = { onItemSelected("Rajesh") })
            Text(text = "Rajesh")
        }
    }


}

@Composable
fun MyTriStatusCheckbox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus }
        )
    }
}

@Composable
fun MyCheckboxWithTextCompleted(checkInfo: CheckInfo) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckboxWithText() {
    var state by rememberSaveable { mutableStateOf(true) }

    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckbox() {
    var state by rememberSaveable { mutableStateOf(true) }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {
        Switch(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Red,
                uncheckedTrackColor = Color.Magenta,
                checkedThumbColor = Color.Green,
                checkedTrackColor = Color.Cyan,
                disabledCheckedThumbColor = Color.Black,
                disabledCheckedTrackColor = Color.Yellow,
                disabledUncheckedThumbColor = Color.White,
                disabledUncheckedTrackColor = Color.Blue
            )
        )
    }
}

@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable { mutableStateOf(0.5f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressStatus)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 8.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar perfil")
        }
    }
}

@Composable
fun MyIcon() {
    // en https://fonts.google.com/icons podemos ver todos los iconos disponibles
    // varios estan en la librería que se debe importar (es un poco pesada)
    // se importa en el build.gradle del módulo app
    // implementation("androidx.compose.material:material-icons-extended:1.5.4")
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "icono", tint = Color.Red)
}

@Composable
fun MyImageAdvance() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Image(
            painter = painterResource(id = R.drawable.jc),
            contentDescription = "ejemplo",
            //modifier = Modifier.clip(RoundedCornerShape(50f))
            modifier = Modifier
                .clip(CircleShape)
                .border(5.dp, Color.Blue, CircleShape)
        )
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.jc),
        contentDescription = "ejemplo",
        alpha = 0.5f
    )
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Blue,
                disabledContentColor = Color.Red
            )
        ) {
            Text(text = "Holis")
        }

        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Los text buttons no tienen borde")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined() {
    var myText by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Hola") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Red
        )
    )
}

@Composable
fun MyTextFieldAdvance() {
    var myText by rememberSaveable { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
        label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}