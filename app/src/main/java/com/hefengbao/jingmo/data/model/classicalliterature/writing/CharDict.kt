/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature.writing

import kotlinx.serialization.Serializable

@Serializable
data class CharDict(
    val OriginalChar: String,
    val Comments: List<CharComment>
)

@Serializable
data class CharComment(
    val Character: String,
    val Origin: String,
    val Explains: List<Explain>
)

@Serializable
data class Explain(
    val Type: String,// Example,Explain,Spell,KXSpell
    val Content: String,
)
