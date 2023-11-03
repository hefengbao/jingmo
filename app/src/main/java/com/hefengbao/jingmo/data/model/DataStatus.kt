package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.DataSetVersion

data class DataStatus(
    /*val poemSynced: Boolean = false,
    val poemCount: Long = 0,*/
    val poemVersion: Int = 0,
    val poemLastReadId: Long = 1,
    /*val tagSynced: Boolean = false,
    val tagCount: Long = 0,*/
    val tagVersion: Int = 0,
    /*val poemTagSynced: Boolean = false,
    val poemTagCount: Long = 0,*/
    val poemTagVersion: Int = 0,
    /*val writerSynced: Boolean = false,
    val writerCount: Long = 0,*/
    val writerVersion: Int = 0,
    /*val poemSentenceSynced: Boolean = false,
    val poemSentenceCount: Long = 0,*/
    val poemSentenceVersion: Int = 1,
    val poemSentenceLastReadId: Long = 1,
    /*val idiomSynced: Boolean = false,
    val idiomCount: Long = 0,*/
    val idiomVersion: Int = 0,
    val idiomLastReadId: Long = 1,
    /*val chineseWisecrackSynced: Boolean = false,
    val chineseWisecrackCount: Long = 0,*/
    val chineseWisecrackVersion: Int = 1,
    val chineseWisecrackLastReadId: Long = 1,
    val captureColor: String,
    val captureBackgroundColor: String
) {
    val allSynced: Boolean = poemVersion == DataSetVersion.poem
            && tagVersion == DataSetVersion.tag
            && poemTagVersion == DataSetVersion.poemTag
            && writerVersion == DataSetVersion.writer
            && poemSentenceVersion == DataSetVersion.poemSentence
            && idiomVersion == DataSetVersion.idiom
            && chineseWisecrackVersion == DataSetVersion.chineseWisecrack

    //poemSynced && tagSynced && poemTagSynced && writerSynced && poemSentenceSynced && idiomSynced && chineseWisecrackSynced
}
