package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.Serializable

@Serializable
data class WordDict(
    val Id: Int,
    val Text: String,
    val CountInPoem: Int,
    val Traditional: String? = null,
    val Explains: List<String>,
    val Categories: List<String>? = null,
    val Spells: String,
    val ContainsUnknownSpell: Boolean
)
