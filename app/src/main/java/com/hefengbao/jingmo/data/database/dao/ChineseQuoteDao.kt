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
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: QuoteEntity)

    @Query("select * from chinese_quote where id = :id limit 1")
    fun get(id: Int): Flow<QuoteEntity?>

    @Query("select * from chinese_quote where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<QuoteEntity>>

    @Query("select * from chinese_quote where id = (select id from chinese_quote order by random() limit 1) limit 1")
    fun random(): Flow<QuoteEntity?>

    @Query("select id from chinese_quote where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_quote where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_quote join chinese_quote_fts on chinese_quote_fts.rowid = chinese_quote.id where chinese_quote_fts match :query")
    fun search(query: String): Flow<List<QuoteEntity>>

    @Query("select q.* from bookmarks b join chinese_quote q on b.bookmarkable_id = q.id and b.bookmarkable_model = 'chinese_quote' order by b.id desc")
    fun bookmarks(): PagingSource<Int, QuoteEntity>

    @Query("select count(*) from chinese_quote")
    fun total(): Flow<Int>

    @Query("delete from chinese_quote")
    suspend fun clear()
}