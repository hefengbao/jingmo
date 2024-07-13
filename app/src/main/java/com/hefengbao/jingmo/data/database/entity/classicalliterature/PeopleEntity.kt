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
import com.hefengbao.jingmo.data.model.classicalliterature.people.Alias
import com.hefengbao.jingmo.data.model.classicalliterature.people.Detail
import com.hefengbao.jingmo.data.model.classicalliterature.people.Hometown

@Entity(tableName = "people")
data class PeopleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "birth_year")
    val birthYear: String?,
    @ColumnInfo(name = "birth_day")
    val birthDay: String?,
    @ColumnInfo(name = "death_year")
    val deathYear: String?,
    @ColumnInfo(name = "death_day")
    val deathDay: String?,
    val dynasty: String,
    val aliases: List<Alias>?,
    val titles: List<String>?,
    val hometown: List<Hometown>?,
    val details: List<Detail>?,
)
