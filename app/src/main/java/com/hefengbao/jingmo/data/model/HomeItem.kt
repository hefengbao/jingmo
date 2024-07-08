/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

data class HomeItem(
    val classicPoem: Boolean = true,
    val writing: Boolean = true,
    val poemSentence: Boolean = true,
    val idiom: Boolean = true,
    val chineseWisecrack: Boolean = true,
    val tongueTwister: Boolean = true,
    val festival: Boolean = true,
    val solarTerm: Boolean = true,
    val chineseKnowledge: Boolean = true,
    val people: Boolean = true,
    val chineseColor: Boolean = true,
    val chineseCharacter: Boolean = true,
    val chineseExpression: Boolean = true,
)
