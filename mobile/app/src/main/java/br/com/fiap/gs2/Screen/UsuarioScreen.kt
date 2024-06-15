package br.com.fiap.gs2.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.gs2.model.Usuario
import br.com.fiap.gs2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun UserListScreen(navController: NavHostController) {
    var users by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        RetrofitClient.usuarioApi.getAllUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    users = response.body() ?: emptyList()
                } else {
                    Toast.makeText(context, "Erro ao buscar usuários", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Toast.makeText(context, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = "USUÁRIOS", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        users.forEach { user ->
            UserItem(user)
            Spacer(modifier = Modifier.height(8.dp))
        }
        FloatingActionButton(
            onClick = { navController.navigate("cadastroUsuario") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
        ) {
            Text("+")
        }
    }
}

@Composable
fun UserItem(user: Usuario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = user.nome, fontSize = 18.sp)
                    Text(text = user.email, fontSize = 14.sp, color = Color.Gray)
                    Text(text = user.celular, fontSize = 14.sp, color = Color.Gray)
                }
                Button(
                    onClick = { /* TODO: Implement action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (user.status == "ativo") Color.Green else Color.Red
                    )
                ) {
                    Text(text = if (user.status == "ativo") "Ativo" else "Desativado", color = Color.White)
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    UserListScreen()
}*/
