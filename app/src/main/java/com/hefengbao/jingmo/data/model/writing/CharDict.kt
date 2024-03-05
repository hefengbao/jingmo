package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.Serializable

@Serializable
data class CharDict(
    val OriginalChar: String,
    val Comments: List<CharComment>
)

@Serializable
data class CharComment(
    val Character: String,
    val Origin: String,
    val Explains: List<Explain>
)

@Serializable
data class Explain(
    val Type: String,// Example,Explain,Spell,KXSpell
    val Content: String,
)
