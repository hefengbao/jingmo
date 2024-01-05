package com.hefengbao.jingmo.data.network

import com.hefengbao.jingmo.data.model.People
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.WritingWrapper

interface Network {
    suspend fun riddles(): List<Riddle>
    suspend fun people(): List<People>
    suspend fun writings(page: Int): WritingWrapper
}