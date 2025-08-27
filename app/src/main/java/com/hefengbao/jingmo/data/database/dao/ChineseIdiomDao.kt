/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseIdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("select * from chinese_idiom where id = :id limit 1")
    fun get(id: Int): Flow<IdiomEntity?>

    @Query("select * from chinese_idiom where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<IdiomEntity>>

    @Query("select i.* from chinese_idiom i where i.id = (select id from chinese_idiom order by random() limit 1)")
    fun random(): Flow<IdiomEntity?>

    @Query("select id from chinese_idiom where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_idiom where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_idiom order by id asc")
    fun list(): PagingSource<Int, IdiomEntity>

    @Query("select * from chinese_idiom where word like :query order by id asc")
    fun search(query: String): PagingSource<Int, IdiomEntity>

    @Query("select i.* from bookmarks b join chinese_idiom i on b.bookmarkable_id = i.id and b.bookmarkable_model = 'chinese_idiom' order by b.id desc")
    fun bookmarks(): PagingSource<Int, IdiomEntity>

    @Query("select count(*) from chinese_idiom")
    fun total(): Flow<Int>

    @Query("delete from chinese_idiom")
    suspend fun clear()
}