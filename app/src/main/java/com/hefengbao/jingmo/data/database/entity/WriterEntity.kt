package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.IntroItem

@Deprecated("")
@Entity(tableName = "writers")
data class WriterEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val avatar: String?,
    val dynasty: String?,
    @ColumnInfo(name = "simple_intro")
    val simpleIntro: String?,
    @ColumnInfo(name = "detail_intro")
    val detailIntro: List<IntroItem>?
)