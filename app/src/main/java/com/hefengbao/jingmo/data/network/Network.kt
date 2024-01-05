package com.hefengbao.jingmo.data.network

import com.hefengbao.jingmo.data.model.People
import com.hefengbao.jingmo.data.model.Riddle

interface Network {
    suspend fun riddles(): List<Riddle>
    suspend fun people(): List<People>
}