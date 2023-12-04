package com.hefengbao.jingmo.data.model

data class Festival(
    val id: Int,
    val name: String,
    val alias: String,
    val desc: String,
    val images: List<String> = emptyList(),
    val url: String,
    val comments: List<String> = emptyList()
)
