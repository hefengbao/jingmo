package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("select i.*,c.collected_at from idioms i left join idiom_collections c on i.id = c.id where i.id = :id")
    fun getIdiom(id: Int): Flow<IdiomWithBookmark>

    @Query("select i.*,c.collected_at from idioms i left join idiom_collections c on i.id = c.id where i.id = (select id from idioms order by random() limit 1)")
    fun random(): Flow<IdiomWithBookmark>

    @Query("select id from idioms where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from idioms where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select id, word from idioms order by id asc")
    fun getSimpleIdiomInfoList(): PagingSource<Int, SimpleIdiomInfo>

    @Query("select id, word from idioms where word like :query order by id asc")
    fun searchSimpleIdiomInfoList(query: String): PagingSource<Int, SimpleIdiomInfo>

    @Query("select id from idioms where id > :id and word like :query order by id asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int

    @Query("select id from idioms where id < :id and word like :query order by id desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int

    @Query("select i.*,c.collected_at from idiom_collections c join idioms i on c.id = i.id order by collected_at desc")
    fun collections(): PagingSource<Int, IdiomWithBookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: IdiomCollectionEntity)

    @Query("delete from idiom_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from idiom_collections where id = :id")
    fun isCollect(id: Int): Flow<IdiomCollectionEntity?>

    @Query("select id from idiom_collections where collected_at < :collectedAt order by collected_at desc limit 1")
    fun getCollectionNextId(collectedAt: Long): Flow<Int?>

    @Query("select id from idiom_collections where collected_at > :collectedAt order by collected_at asc limit 1")
    fun getCollectionPrevId(collectedAt: Long): Flow<Int?>
}