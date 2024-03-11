package com.example.jetpackcomponentscatalog

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldExample() {
    val scaffoldState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContentColor = Color.Black,
                drawerContainerColor = Color.White,
                content = {
                    MyDrawer {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                }
            )
        },
        gesturesEnabled = false,
        content = {
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        onClickIcon = {
                            coroutineScope.launch {
                                scaffoldState.showSnackbar("Has pulsado $it")
                            }
                        },
                        onClickDrawer = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                },
                snackbarHost = { SnackbarHost(scaffoldState) },
                bottomBar = { MyBottomNavigation() },
                floatingActionButton = { MyFAB() },
                floatingActionButtonPosition = FabPosition.Center
            ) {}
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Red,
        titleContentColor = Color.White
    )
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        colors = topAppBarColors,
        navigationIcon = {
            IconButton(
                onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(
                onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "buscar")
            }

            IconButton(
                onClick = { onClickIcon("Eliminar") }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "borrar")
            }
        }
    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableIntStateOf(0) }
    NavigationBar(containerColor = Color.Red) {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home") },
            label = { Text(text = "Home") }
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "favourite") },
            label = { Text(text = "FAV") }
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "person") },
            label = { Text(text = "Person") }
        )
    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = Color.Yellow,
        contentColor = Color.Black
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Primera opción", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Segunda opción", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Tercera opción", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(
            text = "Cuarta opción", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
    }
}