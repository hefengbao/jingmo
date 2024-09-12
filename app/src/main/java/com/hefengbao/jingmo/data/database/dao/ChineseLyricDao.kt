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
import com.hefengbao.jingmo.data.database.entity.chinese.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseLyricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LyricEntity)

    @Query("select * from lyrics where id = :id limit 1")
    fun get(id: Int): Flow<LyricEntity>

    @Query("select l.*from lyrics l where l.id = (select id from lyrics order by random() limit 1) limit 1")
    fun random(): Flow<LyricEntity>

    @Query("select id from lyrics where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from lyrics where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from lyrics join lyrics_fts on lyrics_fts.rowid = lyrics.id where lyrics_fts match :query")
    fun search(query: String): Flow<List<LyricEntity>>

    @Query("select l.* from lyric_collections c join lyrics l on c.id = l.id order by collected_at desc")
    fun collections(): PagingSource<Int, LyricEntity>

    @Query("select count(*) from lyrics")
    fun total(): Flow<Int>

    @Query("select * from lyric_collections where id = :id")
    fun isCollect(id: Int): Flow<LyricCollectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: LyricCollectionEntity)

    @Query("delete from lyric_collections where id = :id")
    suspend fun uncollect(id: Int)
}