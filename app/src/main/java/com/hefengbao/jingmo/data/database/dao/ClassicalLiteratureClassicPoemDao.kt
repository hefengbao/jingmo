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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteratureClassicPoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ClassicPoemEntity)

    @Query("select * from classicalliterature_classicpoem where id = (select p.id from classicalliterature_classicpoem p order by random() limit 1) limit 1")
    fun random(): Flow<ClassicPoemEntity?>

    @Query("select * from classicalliterature_classicpoem where id = :id limit 1")
    fun get(id: Int): Flow<ClassicPoemEntity?>

    @Query("select * from classicalliterature_classicpoem where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<ClassicPoemEntity>>

    @Query("select count(p.id) from classicalliterature_classicpoem p")
    fun total(): Flow<Int>

    @Query("select id from classicalliterature_classicpoem where id > :id order by id limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from classicalliterature_classicpoem where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select p.* from bookmarks b join classicalliterature_classicpoem p on b.bookmarkable_id = p.id and b.bookmarkable_model = 'classicalliterature_classicpoem' order by b.id desc")
    fun bookmarks(): PagingSource<Int, ClassicPoemEntity>

    @Transaction
    @Query("select * from classicalliterature_classicpoem join classicalliterature_classicpoem_fts on classicalliterature_classicpoem_fts.rowid = classicalliterature_classicpoem.id where classicalliterature_classicpoem_fts match :query")
    fun search(query: String): Flow<List<ClassicPoemEntity>>

    @Query("delete from classicalliterature_classicpoem")
    suspend fun clear()

    @Query("select id,title from classicalliterature_classicpoem where title like :query order by id asc")
    fun getIdTitle(query: String): PagingSource<Int, IdTitle>
}