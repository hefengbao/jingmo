/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.serialization.Serializable

/**
 * 句子
 */
@Serializable
data class Quote(
    val id: Int,
    val content: String,
    val author: String,
    val from: String
)

fun Quote.asQuoteEntity() = QuoteEntity(
    id, content, author, from
)
