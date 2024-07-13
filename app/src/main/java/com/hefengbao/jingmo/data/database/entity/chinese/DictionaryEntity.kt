/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.entity.chinese

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class DictionaryEntity(
    @PrimaryKey
    val id: Int,
    val char: String,
    val wubi: String?,
    val radical: String?,
    val stroke: Int,
    val pinyin: String?,
    @ColumnInfo("simple_explanation")
    val simpleExplanation: String?,
    val explanation: String?,
    val loanword: Boolean,
)
