/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteraturePeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PeopleEntity)

    @Query("select * from people where id = (select p.id from people p order by random() limit 1) limit 1")
    fun random(): Flow<PeopleEntity>

    @Query("select * from people where id = :id limit 1")
    fun getPeopleById(id: Int): Flow<PeopleEntity>

    @Query("select * from people where name = :name limit 1")
    fun getPeopleByName(name: String): Flow<PeopleEntity>

    @Query("select * from people where name like :query")
    fun search(query: String): Flow<List<PeopleEntity>>

    @Query("select count(*) from people")
    fun total(): Flow<Int>

    @Query("delete from people")
    suspend fun clear()
}