/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
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

fun ChineseExpression.asChineseExpressionEntity() = ExpressionEntity(
    id, word, pinyin, abbr, explanation
)
