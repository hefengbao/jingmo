package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.DataSetVersion

data class DataStatus(
    val poemVersion: Int = 0,
    val poemLastReadId: Long = 1,
    val tagVersion: Int = 0,
    val poemTagVersion: Int = 0,
    val writerVersion: Int = 0,
    val poemSentenceVersion: Int = 1,
    val poemSentenceLastReadId: Long = 1,
    val idiomVersion: Int = 0,
    val idiomLastReadId: Long = 1,
    val chineseWisecrackVersion: Int = 1,
    val chineseWisecrackLastReadId: Long = 1,
    val captureColor: String,
    val captureBackgroundColor: String,
    val tongueTwisterVersion: Int,
    val tongueTwisterLastReadId: Int = 1,
) {
    val allSynced: Boolean = poemVersion == DataSetVersion.poem
            && tagVersion == DataSetVersion.tag
            && poemTagVersion == DataSetVersion.poemTag
            && writerVersion == DataSetVersion.writer
            && poemSentenceVersion == DataSetVersion.poemSentence
            && idiomVersion == DataSetVersion.idiom
            && chineseWisecrackVersion == DataSetVersion.chineseWisecrack
            && tongueTwisterVersion == DataSetVersion.tongueTwister
}
