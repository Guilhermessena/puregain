package br.com.fiap.gs2.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EstoqueScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Consulta de Estoque",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            var searchText by remember { mutableStateOf("") }
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(56.dp)
                    .border(1.dp, Color.Gray)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProductGrid()
        }
    }
}

@Composable
fun ProductGrid() {
    val produtos = listOf(
        "Produto 1",
        "Produto 2",
        "Produto 3",
        "Produto 4",
        "Produto 5",
        "Produto 6",
        "Produto 7",
        "Produto 8",
        "Produto 9",
        "Produto 10"
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(produtos.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { product ->
                    ProductItem(
                        name = product,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(name: String, modifier: Modifier = Modifier) {
    val cor01 = Color(0xff042c3b)
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = cor01),
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                name,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                "Nome do Produto",
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                "Código do Produto",
                color = Color.White,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, Color.White),
                contentAlignment = Alignment.Center
            ) {
                // Adicionar imagem ou outro conteúdo aqui
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EstoqueScreenPreview() {
    EstoqueScreen(rememberNavController())
}
