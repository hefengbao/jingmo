/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("Book")
    val book: String?,
    @SerialName("Section")
    val section: String?,
    @SerialName("Content")
    val content: String?,
    @SerialName("IsComment")
    val isComment: Boolean = false,
    @SerialName("ReferrenceUrls")
    val referenceUrls: List<String>?
)
