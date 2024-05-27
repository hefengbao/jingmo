package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicPoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ClassicPoemEntity)

    @Query("select * from classic_poems where id = (select p.id from classic_poems p order by random() limit 1) limit 1")
    fun random(): Flow<ClassicPoemEntity>

    @Query("select * from classic_poems where id = :id limit 1")
    fun get(id: Int): Flow<ClassicPoemEntity>

    @Query("select count(p.id) from classic_poems p")
    fun total(): Flow<Int>

    @Query("select id from classic_poems where id > :id order by id limit 1")
    fun getNextId(id: Int): Flow<Int>

    @Query("select id from classic_poems where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int>

    @Query("select * from classic_poem_collections where id = :id limit 1")
    fun isCollect(id: Int): Flow<ClassicPoemCollectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: ClassicPoemCollectionEntity)

    @Query("delete from classic_poem_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select p.* from classic_poem_collections c join classic_poems p on c.id = p.id order by c.collected_at desc")
    fun collections(): PagingSource<Int, ClassicPoemEntity>

    @Transaction
    @Query("select * from classic_poems join classic_poems_fts on classic_poems_fts.rowid = classic_poems.id where classic_poems_fts match :query")
    fun search(query: String): Flow<List<ClassicPoemEntity>>
}