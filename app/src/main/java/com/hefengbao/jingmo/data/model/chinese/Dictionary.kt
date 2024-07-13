/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dictionary(
    val id: Int,
    val char: String,
    val wubi: String?,
    val radical: String?,
    val stroke: Int,
    val pinyin: String?,
    val pinyin2: List<String>?,
    @SerialName("simple_explanation")
    val simpleExplanation: String?,
    val explanation: String?,
    val loanword: Boolean,
)

fun Dictionary.asDictionaryEntity() = DictionaryEntity(
    id, char, wubi, radical, stroke, pinyin, simpleExplanation, explanation, loanword
)

@Serializable
data class DictionaryWrapper(
    val data: List<Dictionary>,
    @SerialName("next_page")
    val nextPage: String?
)