package com.hefengbao.jingmo.data.network

import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.Poem
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.PoemTag
import com.hefengbao.jingmo.data.model.Tag
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.Writer

interface NetworkDataSource {
    suspend fun getPoems(): List<Poem>
    suspend fun getWriters(): List<Writer>
    suspend fun getTags(): List<Tag>
    suspend fun getPoemTagList(): List<PoemTag>
    suspend fun getPoemSentences(): List<PoemSentence>
    suspend fun getChineseWisecracks(): List<ChineseWisecrack>
    suspend fun getIdioms(): List<Idiom>
    suspend fun getTongueTwisters(): List<TongueTwister>
    suspend fun getChinesKnowledge(): List<ChineseKnowledge>
}
