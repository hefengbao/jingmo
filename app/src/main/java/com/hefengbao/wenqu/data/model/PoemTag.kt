package com.hefengbao.wenqu.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoemTag(
    @SerialName("poem_id")
    val poemId: Long,
    @SerialName("tag_id")
    val tagId: Long
)
