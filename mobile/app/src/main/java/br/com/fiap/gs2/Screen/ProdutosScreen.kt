package br.com.fiap.gs2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController



@Composable
fun ProdutosScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("Adicionar")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuButton("Categorias", listOf("Proteinas", "Creatinas", "Pré-treino", "Aminoacidos", "Termogênicos"))
                DropdownMenuButton("Objetivo", listOf("Emagrecimento", "Massa Muscular", "Saúde", "Energia"))
                DropdownMenuButton("Ordenar", listOf("Preço", "Popularidade", "Novidades"))
            }

        }
    }
}

@Composable
fun DropdownMenuButton(text: String, options: List<String>) {
    val cor01 = Color(0xff042c3b)
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = cor01),
        ) {
            Text(text)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProdutosScreentPreview() {
    ProdutosScreen(navController = NavHostController(LocalContext.current))
}
