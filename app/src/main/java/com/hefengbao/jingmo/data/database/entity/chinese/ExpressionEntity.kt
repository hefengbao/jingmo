/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.entity.chinese

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 词语
 */
@Entity(tableName = "chinese_expressions")
data class ExpressionEntity(
    @PrimaryKey
    val id: Int,
    val word: String,
    val pinyin: String,
    val abbr: String? = null,
    val explanation: String? = null
)
