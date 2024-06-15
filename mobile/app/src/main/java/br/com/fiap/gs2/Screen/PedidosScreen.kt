package br.com.fiap.gs2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PedidosScreen(navController: NavController, pedidos: List<Pedido>) {
    val cor01 = Color(0xff042c3b)
    val statusSelecionado = remember { mutableStateOf("todos") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("todos", "pendente", "pago", "cancelado").forEach { status ->
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = cor01),
                    onClick = { statusSelecionado.value = status }
                ) {
                    Text(text = status)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        val pedidosFiltrados = if (statusSelecionado.value == "todos") {
            pedidos
        } else {
            pedidos.filter { it.status == statusSelecionado.value }
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(pedidosFiltrados) { pedido ->
                PedidoItem(pedido = pedido)
                Divider()
            }
        }
        /*Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("addPedido") },
            colors = ButtonDefaults.buttonColors(containerColor = cor01),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Adicionar Pedido")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Adicionar Pedido")
        }*/
    }
}

@Composable
fun PedidoItem(pedido: Pedido) {
    val statusColor = when (pedido.status) {
        "pendente" -> Color.Yellow
        "pago" -> Color.Green
        "cancelado" -> Color.Red
        else -> Color.Gray
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = pedido.nomeCliente,
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { /* Ação ao clicar no nome do cliente */ }
        )
        Text(
            text = pedido.status,
            color = Color.White,
            modifier = Modifier
                .background(statusColor)
                .padding(3.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = pedido.data)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = pedido.valor)
    }
}

data class Pedido(
    val nomeCliente: String,
    val data: String,
    val valor: String,
    val status: String
)

