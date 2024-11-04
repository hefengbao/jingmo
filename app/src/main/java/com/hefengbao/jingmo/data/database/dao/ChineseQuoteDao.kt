/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: QuoteEntity)

    @Query("select * from chinese_quotes where id = :id limit 1")
    fun get(id: Int): Flow<QuoteEntity>

    @Query("select p.*from chinese_quotes p where p.id = (select id from chinese_quotes order by random() limit 1) limit 1")
    fun random(): Flow<QuoteEntity>

    @Query("select id from chinese_quotes where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_quotes where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_quotes join chinese_quotes_fts on chinese_quotes_fts.rowid = chinese_quotes.id where chinese_quotes_fts match :query")
    fun search(query: String): Flow<List<QuoteEntity>>

    @Query("select p.* from chinese_quote_collections c join chinese_quotes p on c.id = p.id order by collected_at desc")
    fun collections(): PagingSource<Int, QuoteEntity>

    @Query("select count(*) from chinese_quotes")
    fun total(): Flow<Int>

    @Query("select * from chinese_quote_collections where id = :id")
    fun isCollect(id: Int): Flow<QuoteCollectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: QuoteCollectionEntity)

    @Query("delete from chinese_quote_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("delete from chinese_quotes")
    suspend fun clear()
}