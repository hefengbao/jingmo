package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "birth_year")
    val birthYear: String?,
    @ColumnInfo(name = "birth_day")
    val birthDay: String?,
    @ColumnInfo(name = "death_year")
    val deathYear: String?,
    @ColumnInfo(name = "death_day")
    val deathDay: String?,
    val dynasty: String,
    val aliases: List<PeopleAlias>?,
    val titles: List<String>?,
    val hometown: List<PeopleHometown>?,
    val details: List<PeopleDetail>?,
)
