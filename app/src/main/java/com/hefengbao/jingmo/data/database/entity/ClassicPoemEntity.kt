/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 经典诗文
 */
@Entity(tableName = "classic_poems")
data class ClassicPoemEntity(
    @PrimaryKey
    val id: Int,
    val dynasty: String,
    val writer: String,
    @ColumnInfo(name = "writer_introduction")
    val writerIntroduction: String?,
    val title: String,
    val subtitle: String?,
    val preface: String?,
    val content: String,
    val annotation: String?,
    val translation: String?,
    @ColumnInfo(name = "creative_background")
    val creativeBackground: String?,
    val explain: String?,
    val comment: String?,
    val collection: String,
    val category: String?
)
