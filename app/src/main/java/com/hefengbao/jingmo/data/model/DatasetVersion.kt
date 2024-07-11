/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

data class DatasetVersion(
    val chineseExpressionVersion: Int = 0,
    val chineseKnowledgeVersion: Int = 0,
    val chineseWisecracksVersion: Int = 0,
    val classicPoemsVersion: Int = 0,
    val dictionaryVersion: Int = 0,
    val idiomsVersion: Int = 0,
    val lyricVersion: Int = 0,
    val peopleVersion: Int = 0,
    val poemSentencesVersion: Int = 0,
    val riddlesVersion: Int = 0,
    val tongueTwistersVersion: Int = 0,
    val writingsVersion: Int = 0,
    val writingsCurrentPage: Int = 1,
    val writingsCurrentCount: Int = 0,
)