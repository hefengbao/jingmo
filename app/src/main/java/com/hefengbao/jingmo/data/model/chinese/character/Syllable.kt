package com.hefengbao.jingmo.data.model.chinese.character

import kotlinx.serialization.Serializable

@Serializable
data class Syllable(
    val alphabet: String,
    val pronunciations: List<Pronunciation>
)

@Serializable
data class Pronunciation(
    val pinyin: String,
    val tones: List<String>
)