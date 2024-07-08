/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

data class Festival(
    val id: Int,
    val name: String,
    val alias: String,
    val desc: String,
    val images: List<String> = emptyList(),
    val url: String,
    val comments: List<String> = emptyList()
)
