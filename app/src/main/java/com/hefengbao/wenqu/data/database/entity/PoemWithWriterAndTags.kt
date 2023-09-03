package com.hefengbao.wenqu.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PoemWithWriterAndTags(
    @Embedded val poem: Poem,

    @Relation(
        parentColumn = "id",
        entityColumn = "writer_id"
    )
    val writer: Writer?,

    @Relation(
        parentColumn = "poem_id",
        entityColumn = "tag_id",
        associateBy = Junction(PoemTagCrossRef::class)
    )
    val tags: List<Tag>
)
