package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Deprecated("")
@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: Long,
    val title: String
)
