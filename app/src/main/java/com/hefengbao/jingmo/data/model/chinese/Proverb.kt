package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.serialization.Serializable

/**
 * 谚语
 */
@Serializable
data class Proverb(
    val id: Int,
    val content: String
)


fun Proverb.asProverbEntity() = ProverbEntity(
    id, content
)