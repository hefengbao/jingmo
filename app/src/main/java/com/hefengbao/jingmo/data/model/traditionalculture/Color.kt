/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.traditionalculture

data class Color(
    val id: String,
    val name: String,
    val traName: String,
    val colorSeries: String,
    val pinyin: String,
    val fontColor: String,
    val isBright: Boolean,
    val rgb: List<Int>,
    val hex: String,
    val cmyk: List<Int>,
    val desc: String,
    val figure: String
)