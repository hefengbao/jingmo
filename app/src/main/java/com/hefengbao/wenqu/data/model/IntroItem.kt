package com.hefengbao.wenqu.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IntroItem(
    val title: String,
    val content: String?
)

