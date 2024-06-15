package br.com.fiap.gs2.network

import br.com.fiap.gs2.model.LoginRequest
import br.com.fiap.gs2.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query





interface UsuarioApi {
    @POST("usuario/create-usuario")
    fun criarUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("usuario/login")
    fun loginUsuario(@Body loginRequest: LoginRequest): Call<Usuario>

    @GET("usuario/login")
    fun loginUsuario(@Query("email") email: String, @Query("senha") senha: String): Call<Usuario>

    @GET("usuario/all")
    fun getAllUsuarios(): Call<List<Usuario>>


}

