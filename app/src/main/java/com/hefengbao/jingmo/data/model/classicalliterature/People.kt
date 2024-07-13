/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature

import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.model.classicalliterature.people.Alias
import com.hefengbao.jingmo.data.model.classicalliterature.people.Detail
import com.hefengbao.jingmo.data.model.classicalliterature.people.Hometown
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 人物
 */
@Serializable
data class People(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String,
    @SerialName("BirthYear")
    val birthYear: String?,
    @SerialName("Birthday")
    val birthDay: String?,
    @SerialName("DeathYear")
    val deathYear: String?,
    @SerialName("Deathday")
    val deathDay: String?,
    @SerialName("Dynasty")
    val dynasty: String,
    @SerialName("Aliases")
    val aliases: List<Alias>?,
    @SerialName("Titles")
    val titles: List<String>?,
    @SerialName("Hometown")
    val hometown: List<Hometown>?,
    @SerialName("Details")
    val details: List<Detail>?
)

fun People.asPeopleEntity() = PeopleEntity(
    id = id,
    name = name,
    birthYear = birthYear,
    birthDay = birthDay,
    deathYear = deathYear,
    deathDay = deathDay,
    dynasty = dynasty,
    aliases = aliases,
    titles = titles,
    hometown = hometown,
    details = details
)
