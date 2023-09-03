package com.hefengbao.wenqu.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Writer(
    @PrimaryKey
    val id: Long,
    val name: String,
    val avatar: String?,
    val dynasty: String?,
    @ColumnInfo(name="simple_intro")
    val simpleIntro: String?,
    @ColumnInfo(name="detail_intro")
    val detailIntro: List<Intro> = emptyList()
)

data class Intro(
    val title: String,
    val content: String?
)
