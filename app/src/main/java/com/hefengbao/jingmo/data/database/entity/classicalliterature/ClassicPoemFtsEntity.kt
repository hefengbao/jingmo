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
import androidx.room.Fts4
import androidx.room.FtsOptions
import androidx.room.PrimaryKey

@Fts4(
    tokenizer = FtsOptions.TOKENIZER_ICU,
    contentEntity = ClassicPoemEntity::class,
)
@Entity(tableName = "classic_poems_fts")
data class ClassicPoemFtsEntity(
    @PrimaryKey
    @ColumnInfo("rowid")
    val id: Int,
    val writer: String,
    val title: String,
    val content: String,
)
