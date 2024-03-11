package com.example.jetpackcomponentscatalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomponentscatalog.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList =
        listOf("Pikachu", "Pidgeotto", "Butterfree ", "Bulbasaur", "Charmander", "Squirtle")
    LazyColumn {
        item { Text(text = "Header") }
        items(myList) {
            Text(text = "Hola! Me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperheroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheros()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.publisher, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperheroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperheros().groupBy { it.publisher }
    
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach {(publisher, mySuperhero) ->
            stickyHeader { 
                Text(text = publisher, modifier = Modifier.fillMaxWidth().background(Color.Cyan), fontSize = 24.sp, color = Color.Red)
            }
            items(mySuperhero) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, it.publisher, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun SuperheroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(getSuperheros()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.publisher, Toast.LENGTH_SHORT).show()
            }
        }
    }, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp))
}

@Composable
fun SuperheroWithSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheros()) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, it.publisher, Toast.LENGTH_SHORT).show()
                }
            }
        }
        // este devuelve numeros a medida que se scrollea. 0 al inicio, y un numero mayor al final
        // del scroll. así puedo saber si se scrollea para abajo y para arriba.
        //rvState.firstVisibleItemScrollOffset

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showButton) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Super botón")
            }
        }
    }
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            //.width(200.dp)
            .clickable { onItemSelected(superhero) }
        /*.padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp)*/) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Superhero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperheros(): List<Superhero> {
    return listOf(
        Superhero("Spider-Man", "Peter Benjamin Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        Superhero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        Superhero("Flash", "Jason \"Jay\" Peter Garrick", "DC", R.drawable.flash),
        Superhero("Green Lantern", "Alan Ladd Wellington Scott", "DC", R.drawable.green_lantern),
        Superhero("Wonder Woman", "Diana de Temiscira", "DC", R.drawable.wonder_woman)
    )
}