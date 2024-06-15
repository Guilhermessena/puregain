package br.com.fiap.gs2



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.gs2.Screen.CadastrarProdutosScreen
import br.com.fiap.gs2.Screen.CadastroUsuarioScreen
import br.com.fiap.gs2.Screen.EstoqueScreen


import br.com.fiap.gs2.Screen.FerramentasScreen
import br.com.fiap.gs2.Screen.LoginScreen
import br.com.fiap.gs2.Screen.Pedido
import br.com.fiap.gs2.Screen.PedidosScreen
import br.com.fiap.gs2.Screen.ProdutosScreen
import br.com.fiap.gs2.Screen.UserListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login_screen") {
                composable("login_screen") { LoginScreen(navController) }
                composable("ferramentas_screen") { FerramentasScreen(navController) }
                composable("userList") { UserListScreen(navController) }
                composable("cadastroUsuario") { CadastroUsuarioScreen(navController) }
                composable("produtos_screen") { ProdutosScreen(navController) }
                composable("Adicionar") { CadastrarProdutosScreen(navController) }
                composable("estoque_screen") { EstoqueScreen(navController) }
                composable("pedidos_screen") { PedidosScreen(navController, getSamplePedidos()) }
            }
        }
    }

    // Função para obter uma lista de pedidos de exemplo
    private fun getSamplePedidos(): List<Pedido> {
        return listOf(
            Pedido("Cliente A", "01/01/2024", "R$ 1000,00", "pendente"),
            Pedido("Cliente B", "02/01/2024", "R$ 1500,00", "pago"),
            Pedido("Cliente C", "03/01/2024", "R$ 2000,00", "cancelado"),
            Pedido("Cliente A", "01/01/2024", "R$ 1000,00", "pendente"),
            Pedido("Cliente B", "02/01/2024", "R$ 1500,00", "pago"),
            Pedido("Cliente C", "03/01/2024", "R$ 2000,00", "cancelado"),
            Pedido("Cliente A", "01/01/2024", "R$ 1000,00", "pendente"),
            Pedido("Cliente B", "02/01/2024", "R$ 1500,00", "pago"),
            Pedido("Cliente C", "03/01/2024", "R$ 2000,00", "cancelado"),
            Pedido("Cliente A", "01/01/2024", "R$ 1000,00", "pendente"),
            Pedido("Cliente B", "02/01/2024", "R$ 1500,00", "pago"),
            Pedido("Cliente C", "03/01/2024", "R$ 2000,00", "cancelado")
        )
    }
}

