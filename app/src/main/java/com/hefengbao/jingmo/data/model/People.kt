package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.PeopleEntity
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
    @SerialName("BirthDay")
    val birthDay: String?,
    @SerialName("DeathYear")
    val deathYear: String?,
    @SerialName("DeathDay")
    val deathDay: String?,
    @SerialName("Dynasty")
    val dynasty: String,
    @SerialName("Aliases")
    val aliases: List<PeopleAlias>?,
    @SerialName("Titles")
    val titles: List<String>?,
    @SerialName("Hometown")
    val hometown: List<PeopleHometown>?,
    @SerialName("Detail")
    val details: List<PeopleDetail>?
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
