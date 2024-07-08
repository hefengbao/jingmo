/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dataset(
    val id: Int,
    val name: String,
    val count: Int,
    val version: Int
)