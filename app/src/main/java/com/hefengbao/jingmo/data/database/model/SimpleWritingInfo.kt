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

data class SimpleWritingInfo(
    @ColumnInfo(name = "rowid")
    val id: Int,
    val author: String,
    val dynasty: String,
    val type: String,
    val title: String
)
