package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.ChineseColor

interface ChineseColorRepository {
    fun getList(): List<ChineseColor>
}