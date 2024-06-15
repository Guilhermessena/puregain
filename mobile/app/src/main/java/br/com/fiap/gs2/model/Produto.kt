package br.com.fiap.gs2.model

data class Produto(
    val id: Int = 0,
    val nome: String,
    val codigo: String,
    val precoDe: Double,
    val precoPor: Double,
    val categoria: String,
    val objetivo: String,
    val descricao: String
)
