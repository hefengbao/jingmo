package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riddles")
data class RiddleEntity(
    @PrimaryKey
    val id: Int,
    val puzzle: String,
    val answer: String
)