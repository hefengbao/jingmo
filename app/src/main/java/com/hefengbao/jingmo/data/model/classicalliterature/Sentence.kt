/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature

import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sentence(
    val id: Int,
    val content: String,
    val from: String,
    @SerialName("poem_id")
    val poemId: Int? = null
)

fun Sentence.asSentenceEntity() = SentenceEntity(
    id, content, from, poemId
)
