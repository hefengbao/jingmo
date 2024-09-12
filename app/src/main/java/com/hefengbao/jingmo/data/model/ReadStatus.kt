/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information: Int, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

data class ReadStatus(
    val chineseKnowledgeLastReadId: Int,
    val chineseLyricLastReadId: Int,
    val chineseProverbLastReadId: Int,
    val chineseWisecracksLastReadId: Int,
    val classicPoemsLastReadId: Int,
    val idiomsLastReadId: Int,
    val peopleLastReadId: Int,
    val poemSentencesLastReadId: Int,
    val riddlesLastReadId: Int,
    val tongueTwistersLastReadId: Int,
    val writingsLastReadId: Int,
)
