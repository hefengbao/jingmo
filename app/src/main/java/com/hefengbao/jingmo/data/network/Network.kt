package com.hefengbao.jingmo.data.network

import com.hefengbao.jingmo.data.model.Riddle

interface Network {
    suspend fun riddles(): List<Riddle>
}