package com.hefengbao.jingmo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IntroItem(
    val title: String,
    val content: String?
)

