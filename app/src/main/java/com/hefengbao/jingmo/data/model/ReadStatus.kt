/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

data class ReadStatus(
    val chineseKnowledgeLastReadId: Int = 1,
    val chineseWisecracksLastReadId: Int = 1,
    val classicPoemsLastReadId: Int = 1,
    val idiomsLastReadId: Int = 1,
    val peopleLastReadId: Int = 1,
    val poemSentencesLastReadId: Int = 1,
    val riddlesLastReadId: Int = 1,
    val tongueTwistersLastReadId: Int = 1,
    val writingsLastReadId: Int = 1,
)
