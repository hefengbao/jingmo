package com.hefengbao.jingmo.data.model

data class ChineseColor(
    val id: String,
    val name: String,
    val traName: String,
    val colorSeries: String,
    val pinyin: String,
    val fontColor: String,
    val isBright: Boolean,
    val rgb: List<Int>,
    val hex: String,
    val cmyk: List<Int>,
    val desc: String,
    val figure: String
)