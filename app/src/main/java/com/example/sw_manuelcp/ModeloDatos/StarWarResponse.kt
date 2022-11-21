package com.example.sw_manuelcp.ModeloDatos

data class StarWarResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)