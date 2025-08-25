/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseLyricDao
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LyricRepositoryImpl @Inject constructor(
    private val chineseLyricDao: ChineseLyricDao
) : LyricRepository {
    override fun get(id: Int): Flow<LyricEntity?> = chineseLyricDao.get(id)

    override fun getRandom(): Flow<LyricEntity?> = chineseLyricDao.random()

    override fun search(query: String): Flow<List<LyricEntity>> = chineseLyricDao.search(query)

    override fun total(): Flow<Int> = chineseLyricDao.total()

    override fun prevId(id: Int): Flow<Int?> = chineseLyricDao.getPrevId(id)

    override fun nextId(id: Int): Flow<Int?> = chineseLyricDao.getNextId(id)

    override fun bookmarks(): Flow<PagingData<LyricEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { chineseLyricDao.bookmarks() }
    ).flow
}