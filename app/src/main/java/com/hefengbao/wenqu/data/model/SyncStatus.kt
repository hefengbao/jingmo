package com.hefengbao.wenqu.data.model

data class DataStatus(
    val poemSynced: Boolean = false,
    val poemCount: Long = 0,
    val poemLastReadId: Long = 1,
    val tagSynced: Boolean = false,
    val tagCount: Long = 0,
    val poemTagSynced: Boolean = false,
    val poemTagCount: Long = 0,
    val writerSynced: Boolean = false,
    val writerCount: Long = 0,
    val poemSentenceSynced: Boolean = false,
    val poemSentenceCount: Long = 0,
    val poemSentenceLastReadId: Long = 1,
    val idiomSynced: Boolean = false,
    val idiomCount: Long = 0,
    val idiomLastReadId: Long = 1,
    val chineseWisecrackSynced: Boolean = false,
    val chineseWisecrackCount: Long = 0,
    val chineseWisecrackLastReadId: Long = 1,
) {
    val allSynced: Boolean =
        poemSynced && tagSynced && poemTagSynced && writerSynced && poemSentenceSynced && idiomSynced && chineseWisecrackSynced
}
