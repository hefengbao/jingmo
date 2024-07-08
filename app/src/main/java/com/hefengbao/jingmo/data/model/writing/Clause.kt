/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Clause(
    @SerialName("Content")
    val content: String,
    @SerialName("Comments")
    val comments: List<Comment>?,
    @SerialName("BreakAfter")
    val breakAfter: Int?
)
