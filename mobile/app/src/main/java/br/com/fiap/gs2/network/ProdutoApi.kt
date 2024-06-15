package br.com.fiap.gs2.network

import br.com.fiap.gs2.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ProdutoApi {
    @POST("produto/create-produto")
    fun criarProduto(@Body produto: Produto): Call<String>
}
