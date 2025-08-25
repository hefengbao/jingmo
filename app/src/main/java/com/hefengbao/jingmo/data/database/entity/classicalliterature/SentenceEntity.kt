/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.entity.classicalliterature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classicalliterature_sentence")
data class SentenceEntity(
    @PrimaryKey
    val id: Int,
    val content: String,
    val from: String,
    @ColumnInfo("poem_id")
    val poemId: Int?
)
