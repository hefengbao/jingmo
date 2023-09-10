package com.hefengbao.jingmo.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PoemWithWriterAndTags(
    @Embedded val poemEntity: PoemEntity,

    @Relation(
        parentColumn = "writer_id",
        entityColumn = "id"
    )
    val writerEntity: WriterEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PoemTagCrossRef::class,
            parentColumn = "poem_id",
            entityColumn = "tag_id"
        )
    )
    val tags: List<TagEntity> = emptyList()
)
