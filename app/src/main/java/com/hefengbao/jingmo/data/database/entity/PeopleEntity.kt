package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.PeopleAlias
import com.hefengbao.jingmo.data.model.PeopleDetail
import com.hefengbao.jingmo.data.model.PeopleHometown

@Entity(tableName = "people")
data class PeopleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val birthYear: String?,
    val birthDay: String?,
    val deathYear: String?,
    val deathDay: String?,
    val dynasty: String,
    val aliases: List<PeopleAlias>?,
    val titles: List<String>?,
    val hometown: List<PeopleHometown>?,
    val details: List<PeopleDetail>?,
)
