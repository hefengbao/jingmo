/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class PoemSentenceWithBookmark(
    val id: Int,
    val content: String,
    val from: String,
    @ColumnInfo("poem_id")
    val poemId: Int?,
    @ColumnInfo("collected_at")
    val collectedAt: Long?
)
