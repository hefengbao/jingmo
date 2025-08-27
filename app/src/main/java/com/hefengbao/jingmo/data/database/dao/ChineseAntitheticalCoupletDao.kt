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
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseAntitheticalCoupletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AntitheticalCoupletEntity)

    @Query("select * from chinese_antitheticalcouplet where id = :id limit 1")
    fun get(id: Int): Flow<AntitheticalCoupletEntity?>

    @Query("select * from chinese_antitheticalcouplet where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<AntitheticalCoupletEntity>>

    @Query("select a.* from chinese_antitheticalcouplet a where a.id = (select id from chinese_antitheticalcouplet order by random() limit 1)")
    fun random(): Flow<AntitheticalCoupletEntity?>

    @Query("select id from chinese_antitheticalcouplet where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_antitheticalcouplet where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_antitheticalcouplet order by id asc")
    fun list(): PagingSource<Int, AntitheticalCoupletEntity>

    @Query("select * from chinese_antitheticalcouplet where body like :query order by id asc")
    fun search(query: String): PagingSource<Int, AntitheticalCoupletEntity>

    @Query("select a.* from bookmarks b join chinese_antitheticalcouplet a on b.bookmarkable_id = a.id and bookmarkable_model = 'chinese_antitheticalcouplet' order by b.id desc")
    fun bookmarks(): PagingSource<Int, AntitheticalCoupletEntity>

    @Query("select count(*) from chinese_antitheticalcouplet")
    fun total(): Flow<Int>

    @Query("delete from chinese_antitheticalcouplet")
    suspend fun clear()
}