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

data class ChineseWisecrackWithBookmark(
    val id: Int,
    val riddle: String,
    val answer: String,
    @ColumnInfo(name = "first_word")
    val firstWord: String,
    @ColumnInfo(name = "first_letter")
    val firstLetter: String,
    @ColumnInfo("collected_at")
    val collectedAt: Long?
)
