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
    val chinaWorldCulturalHeritage: Boolean = true,
    val classicalLiteratureClassicPoem: Boolean = true,
    val classicalLiteraturePeople: Boolean = true,
    val classicalLiteratureSentence: Boolean = true,
    val classicalLiteratureWriting: Boolean = true,
    val chineseAntitheticalCouplet: Boolean = true,
    val chineseCharacter: Boolean = true,
    val chineseExpression: Boolean = true,
    val chineseIdiom: Boolean = true,
    val chineseKnowledge: Boolean = true,
    val chineseLyric: Boolean = true,
    val chineseProverb: Boolean = true,
    val chineseQuote: Boolean = true,
    val chineseRiddle: Boolean = true,
    val chineseTongueTwister: Boolean = true,
    val chineseWisecrack: Boolean = true,
    val traditionalCultureColor: Boolean = true,
    val traditionalCultureFestival: Boolean = true,
    val traditionalCultureSolarTerm: Boolean = true,
) {
    val chinaGroup = chinaWorldCulturalHeritage

    val classicalLiteratureGroup = classicalLiteratureClassicPoem ||
            classicalLiteraturePeople ||
            classicalLiteratureSentence ||
            classicalLiteratureWriting

    val chineseGroup = chineseAntitheticalCouplet ||
            chineseCharacter ||
            chineseExpression ||
            chineseIdiom ||
            chineseKnowledge ||
            chineseLyric ||
            chineseProverb ||
            chineseQuote ||
            chineseRiddle ||
            chineseTongueTwister ||
            chineseWisecrack

    val traditionalCultureGroup = traditionalCultureColor ||
            traditionalCultureFestival ||
            traditionalCultureSolarTerm
}
