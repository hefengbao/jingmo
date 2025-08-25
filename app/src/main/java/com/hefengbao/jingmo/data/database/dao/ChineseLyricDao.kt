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
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseLyricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LyricEntity)

    @Query("select * from chinese_lyric where id = :id limit 1")
    fun get(id: Int): Flow<LyricEntity?>

    @Query("select l.*from chinese_lyric l where l.id = (select id from chinese_lyric order by random() limit 1) limit 1")
    fun random(): Flow<LyricEntity?>

    @Query("select id from chinese_lyric where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_lyric where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_lyric join chinese_lyric_fts on chinese_lyric_fts.rowid = chinese_lyric.id where chinese_lyric_fts match :query")
    fun search(query: String): Flow<List<LyricEntity>>

    @Query("select l.* from bookmarks b join chinese_lyric l on b.bookmarkable_id = l.id and b.bookmarkable_model = 'chinese_lyric' order by b.id desc")
    fun bookmarks(): PagingSource<Int, LyricEntity>

    @Query("select count(*) from chinese_lyric")
    fun total(): Flow<Int>

    @Query("delete from chinese_lyric")
    suspend fun clear()
}