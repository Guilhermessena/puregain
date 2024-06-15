package br.com.fiap.gs2.model

data class Usuario(
    val id: Int = 0,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val celular: String,
    val senha: String,
    val adm: Int,
    val status: String
)
