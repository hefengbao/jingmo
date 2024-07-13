/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import kotlinx.serialization.Serializable

@Serializable
data class Lyric(
    val id: Int,
    val title: String,
    val writer: String?,
    val singer: String?,
    val content: String
)

fun Lyric.asLyricEntity() = LyricEntity(
    id, title, writer, singer, content
)