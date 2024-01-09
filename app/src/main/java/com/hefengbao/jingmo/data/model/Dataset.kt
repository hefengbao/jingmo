package com.hefengbao.jingmo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dataset(
    val id: Int,
    val name: String,
    val count: Int,
    val version: Int
)