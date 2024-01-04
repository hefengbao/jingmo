package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import kotlinx.serialization.Serializable

@Serializable
data class Riddle(
    val id: Int,
    val puzzle: String,
    val answer: String
)

fun Riddle.asRiddleEntity() = RiddleEntity(
    id, puzzle, answer
)

