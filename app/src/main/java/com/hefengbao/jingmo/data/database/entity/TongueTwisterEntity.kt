package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tongue_twisters")
data class TongueTwisterEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val content2: String?,
)
