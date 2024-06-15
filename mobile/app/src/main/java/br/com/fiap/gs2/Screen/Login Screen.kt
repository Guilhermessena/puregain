package br.com.fiap.gs2.Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.gs2.R
import br.com.fiap.gs2.model.Usuario
import br.com.fiap.gs2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val cor01 = Color(0xff042c3b)
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pure),
                contentDescription = "",
                modifier = Modifier
                    .offset(y = 20.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .offset(y = 110.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Button(
                    onClick = {
                        Log.d("LoginScreen", "Login button clicked")
                        RetrofitClient.usuarioApi.loginUsuario(email, senha)
                            .enqueue(object : Callback<Usuario> {
                                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                                    Log.d("LoginScreen", "Received response: ${response.isSuccessful}")
                                    if (response.isSuccessful) {
                                        val usuarioLogado = response.body()
                                        if (usuarioLogado != null) {
                                            Log.d("LoginScreen", "Login successful for user: ${usuarioLogado.nome}")
                                            Toast.makeText(context, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                                            navController.navigate("ferramentas_screen") {
                                                popUpTo("login_screen") { inclusive = true }
                                            }
                                        } else {
                                            Log.e("LoginScreen", "Erro ao recuperar dados do usuário")
                                            Toast.makeText(context, "Erro ao recuperar dados do usuário", Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        Log.e("LoginScreen", "Email ou senha incorretos")
                                        Toast.makeText(context, "Email ou senha incorretos", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                                    Log.e("LoginScreen", "Erro na requisição: ${t.message}")
                                    Toast.makeText(context, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = cor01),
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
