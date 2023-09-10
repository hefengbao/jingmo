package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.WriterEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Writer(
    val id: Long,
    val name: String,
    val avatar: String?,
    val dynasty: String?,
    @SerialName("simple_intro")
    val simpleIntro: String?,
    @SerialName("detail_intro")
    val detailIntro: List<IntroItem>?
)

fun Writer.toWriterEntity() = WriterEntity(
    id, name, avatar, dynasty, simpleIntro, detailIntro
)