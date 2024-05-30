package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import kotlinx.serialization.Serializable

/**
 * 词语
 */
@Serializable
data class ChineseExpression(
    val id: Int,
    val word: String,
    val pinyin: String,
    val abbr: String? = null,
    val explanation: String? = null
)

fun ChineseExpression.asChineseExpressionEntity() = ChineseExpressionEntity(
    id, word, pinyin, abbr, explanation
)
