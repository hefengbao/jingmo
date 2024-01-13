package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoemSentence(
    val id: Int,
    val content: String,
    val from: String,
    @SerialName("poem_id")
    val poemId: Int? = null
)

fun PoemSentence.asPoemSentenceEntity() = PoemSentenceEntity(
    id, content, from, poemId
)
