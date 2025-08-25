/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 诗歌
 */
@Serializable
data class ModernPoetry(
    val id: Int,
    val title: String,
    val author: String,
    val content: String,
    val zhu: String?,
    val yi: String?,
    val shang: String?,
    @SerialName("author_info")
    val authorInfo: String?
)

fun ModernPoetry.asModernPoetryEntity() = ModernPoetryEntity(
    id, title, author, content, zhu, yi, shang, authorInfo
)