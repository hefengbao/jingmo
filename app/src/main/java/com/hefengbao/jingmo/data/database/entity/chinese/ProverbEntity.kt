package com.hefengbao.jingmo.data.database.entity.chinese

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chinese_proverb")
data class ProverbEntity(
    @PrimaryKey
    val id: Int,
    val content: String
)
