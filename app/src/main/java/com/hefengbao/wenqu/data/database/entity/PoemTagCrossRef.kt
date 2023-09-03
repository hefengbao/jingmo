package com.hefengbao.wenqu.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["poem_id","tag_id"])
data class PoemTagCrossRef(
    @ColumnInfo("poem_id")
    val poemId: Long,
    @ColumnInfo("tag_id")
    val tagId: Long
)
