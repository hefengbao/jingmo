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
    val chineseAntitheticalCoupletLastReadId: Int,
    val chineseIdiomLastReadId: Int,
    val chineseKnowledgeLastReadId: Int,
    val chineseLyricLastReadId: Int,
    val chineseModernPoetryLastReadId: Int,
    val chineseProverbLastReadId: Int,
    val chineseQuoteLastReadId: Int,
    val chineseRiddleLastReadId: Int,
    val chineseTongueTwisterLastReadId: Int,
    val chineseWisecrackLastReadId: Int,
    val classicLiteratureClassicPoemLastReadId: Int,
    val classicLiteraturePeopleLastReadId: Int,
    val classicLiteratureSentenceLastReadId: Int,
    val classicLiteratureWritingsLastReadId: Int,
)
