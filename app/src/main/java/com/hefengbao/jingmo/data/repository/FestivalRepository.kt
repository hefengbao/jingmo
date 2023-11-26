package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.Festival

interface FestivalRepository {
    fun getList(): List<Festival>
}