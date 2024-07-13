/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import kotlinx.serialization.Serializable

@Serializable
data class TongueTwister(
    val id: Int,
    val title: String,
    val content: String,
    val content2: String?,
)

fun TongueTwister.asTongueTwisterEntity() = TongueTwisterEntity(
    id = id,
    title = title,
    content = content,
    content2 = content2,
)
