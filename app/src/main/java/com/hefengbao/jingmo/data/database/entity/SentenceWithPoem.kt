package com.hefengbao.jingmo.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

@Deprecated("")
data class SentenceWithPoem(
    @Embedded val sentence: PoemSentenceEntity,
    @Relation(
        parentColumn = "poem_id",
        entityColumn = "id"
    )
    val poem: PoemEntity?
)
