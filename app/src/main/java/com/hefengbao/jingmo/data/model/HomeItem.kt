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
    val classicalLiteratureClassicPoem: Boolean = true,
    val classicalLiteraturePeople: Boolean = true,
    val classicalLiteratureSentence: Boolean = true,
    val classicalLiteratureWriting: Boolean = true,
    val chineseCharacter: Boolean = true,
    val chineseExpression: Boolean = true,
    val chineseIdiom: Boolean = true,
    val chineseKnowledge: Boolean = true,
    val chineseLyric: Boolean = true,
    val chineseProverb: Boolean = true,
    val chineseTongueTwister: Boolean = true,
    val chineseWisecrack: Boolean = true,
    val traditionalCultureColor: Boolean = true,
    val traditionalCultureFestival: Boolean = true,
    val traditionalCultureSolarTerm: Boolean = true,
) {
    val classicalLiteratureGroup = classicalLiteratureClassicPoem ||
            classicalLiteraturePeople ||
            classicalLiteratureSentence ||
            classicalLiteratureWriting

    val chineseGroup = chineseCharacter ||
            chineseExpression ||
            chineseIdiom ||
            chineseKnowledge ||
            chineseLyric ||
            chineseProverb ||
            chineseTongueTwister ||
            chineseWisecrack

    val traditionalCultureGroup = traditionalCultureColor ||
            traditionalCultureFestival ||
            traditionalCultureSolarTerm
}
