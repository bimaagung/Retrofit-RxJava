package com.otetcode.tryretrofitrxjava.Model


import com.google.gson.annotations.SerializedName

data class Post(
    val body: String = "",
    val id: Int = 0,
    val title: String = "",
    val userId: Int = 0
)