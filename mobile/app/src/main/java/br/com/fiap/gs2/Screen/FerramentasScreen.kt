package br.com.fiap.gs2.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun FerramentasScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GridButton("Pedidos", navController)
                    GridButton("Usuário", navController)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GridButton("Produtos", navController)
                    GridButton("Estoque", navController)
                }
            }
            BottomNavigationBar(navController)
        }
    }
}

@Composable
fun GridButton(text: String, navController: NavHostController? = null) {
    val cor01 = Color(0xff042c3b)
    Button(
        onClick = {
            when (text) {
                "Pedidos" -> navController?.navigate("pedidos_screen")
                "Usuário" -> navController?.navigate("userList")
                "Produtos" -> navController?.navigate("produtos_screen")
                "Estoque" -> navController?.navigate("estoque_screen")
                else -> {}
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = cor01),
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentDestination?.route == "produtos_screen",
            onClick = { navController.navigate("produtos_screen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Ferramentas") },
            label = { Text("Ferramentas") },
            selected = navController.currentDestination?.route == "ferramentas_screen",
            onClick = { navController.navigate("ferramentas_screen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            selected = navController.currentDestination?.route == "userList",
            onClick = { navController.navigate("userList") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FerramentasPreview() {
    FerramentasScreen(rememberNavController())
}
