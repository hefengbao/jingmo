package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChineseExpressionWrapper(
    val data: List<ChineseExpression>,
    @SerialName("next_page")
    val nextPage: String?
)
