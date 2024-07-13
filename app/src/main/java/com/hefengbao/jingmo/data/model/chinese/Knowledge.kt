/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import kotlinx.serialization.Serializable

@Serializable
data class ChineseKnowledge(
    val id: Int,
    val content: String,
    val label: String,
    val url: String?,
)

fun ChineseKnowledge.asChineseKnowledgeEntity() = KnowledgeEntity(
    id, content, label, url
)