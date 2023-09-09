package com.hefengbao.wenqu.data.network

import com.hefengbao.wenqu.data.model.ChineseWisecrack
import com.hefengbao.wenqu.data.model.Idiom
import com.hefengbao.wenqu.data.model.Poem
import com.hefengbao.wenqu.data.model.PoemSentence
import com.hefengbao.wenqu.data.model.PoemTag
import com.hefengbao.wenqu.data.model.Tag
import com.hefengbao.wenqu.data.model.Writer

interface NetworkDataSource {
    suspend fun getPoems(): List<Poem>
    suspend fun getWriters(): List<Writer>
    suspend fun getTags(): List<Tag>
    suspend fun getPoemTagList(): List<PoemTag>
    suspend fun getPoemSentences(): List<PoemSentence>
    suspend fun getChineseWisecracks(): List<ChineseWisecrack>
    suspend fun getIdioms(): List<Idiom>
}
