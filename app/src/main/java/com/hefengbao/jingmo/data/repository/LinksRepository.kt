package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.Link

interface LinksRepository {
    fun getList(): List<Link>
}