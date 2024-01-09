package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 歇后语
 */
@Serializable
data class ChineseWisecrack(
    val id: Long,
    val riddle: String,
    val answer: String,
    @SerialName("first_word")
    val firstWord: String = "",
    @SerialName("first_letter")
    val firstLetter: String = ""
)

fun ChineseWisecrack.asChineseWisecrackEntity() = ChineseWisecrackEntity(
    id, riddle, answer, firstWord, firstLetter
)
