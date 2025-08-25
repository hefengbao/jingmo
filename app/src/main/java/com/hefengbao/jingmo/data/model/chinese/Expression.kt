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
data class Expression(
    val id: Int,
    val word: String,
    val pinyin: String,
    val explanation: String?,
    val source: IdiomSource?,
    val quote: IdiomQuote?,
    val example: IdiomExample?,
    val similar: List<String>?,
    val opposite: List<String>?,
    val usage: String?,
    val story: List<String>?,
    val notice: String?,
)

fun Expression.asExpressionEntity() = ExpressionEntity(
    id, word, pinyin, explanation, source, quote, example, similar, opposite, usage, story, notice
)
