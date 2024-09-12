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
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseIdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("select i.* from idioms i left join idiom_collections c on i.id = c.id where i.id = :id")
    fun get(id: Int): Flow<IdiomEntity>

    @Query("select i.* from idioms i where i.id = (select id from idioms order by random() limit 1)")
    fun random(): Flow<IdiomEntity>

    @Query("select id from idioms where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from idioms where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from idioms order by id asc")
    fun list(): PagingSource<Int, IdiomEntity>

    @Query("select * from idioms where word like :query order by id asc")
    fun search(query: String): PagingSource<Int, IdiomEntity>

    @Query("select i.* from idiom_collections c join idioms i on c.id = i.id order by collected_at desc")
    fun collections(): PagingSource<Int, IdiomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: IdiomCollectionEntity)

    @Query("delete from idiom_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from idiom_collections where id = :id")
    fun isCollect(id: Int): Flow<IdiomCollectionEntity?>

    @Query("select count(*) from idioms")
    fun total(): Flow<Int>

    @Query("delete from idioms")
    suspend fun clear()
}