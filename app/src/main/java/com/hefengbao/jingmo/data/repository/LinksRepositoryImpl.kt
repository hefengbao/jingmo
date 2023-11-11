package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.Link
import javax.inject.Inject

class LinksRepositoryImpl @Inject constructor() : LinksRepository {
    override fun getList(): List<Link> {
        return listOf(
            Link(
                title = "鲁迅博物馆",
                desc = "鲁迅说过这话吗？",
                url = "http://www.luxunmuseum.com.cn/cx/"
            ),
            Link(
                title = "中国大百科全书",
                desc = "第三版网络版",
                url = "https://www.zgbk.com"
            ),
        )
    }
}