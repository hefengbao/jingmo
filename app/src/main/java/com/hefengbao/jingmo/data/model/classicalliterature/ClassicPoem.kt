/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model.classicalliterature

import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 经典诗文
 */
@Serializable
data class ClassicPoem(
    val id: Int,
    val dynasty: String, // 朝代
    val writer: String, // 作者
    @SerialName("writer_introduction")
    val writerIntroduction: String?, // 作者简介
    val title: String, // 标题
    val subtitle: String?, // 副标题
    val preface: String?, // 序言
    val content: String, // 内容
    val annotation: String?, // 注释
    val translation: String?, // 译文
    @SerialName("creative_background")
    val creativeBackground: String?, // 创作背景
    val explain: String?, // 赏析
    val comment: String?, // 点评
    val collection: String, // 合集
    val category: String?, // 分类
    val bookmark: Boolean = false,
)

fun ClassicPoem.asClassicPoemEntity() = ClassicPoemEntity(
    id,
    dynasty,
    writer,
    writerIntroduction,
    title,
    subtitle,
    preface,
    content,
    annotation,
    translation,
    creativeBackground,
    explain,
    comment,
    collection,
    category
)
