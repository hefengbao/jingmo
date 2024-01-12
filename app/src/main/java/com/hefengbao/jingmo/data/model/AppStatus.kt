package com.hefengbao.jingmo.data.model

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
    val chineseKnowledgeVersion: Int,
    val chineseKnowledgeLastReadId: Int = 1,
    val riddleVersion: Int,
    val riddleLastReadId: Int = 1,
) {
    val allSynced: Boolean = true
}
