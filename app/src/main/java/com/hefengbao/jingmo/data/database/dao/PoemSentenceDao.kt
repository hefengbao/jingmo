package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.model.PoemSentenceWithBookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PoemSentenceEntity)

    @Query("select p.*,c.collected_at from poem_sentences p left join poem_sentence_collections c on p.id = c.id where p.id = :id")
    fun getSentence(id: Int): Flow<PoemSentenceWithBookmark>

    @Query("select id from poem_sentences where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from poem_sentences where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from poem_sentences where content like :query")
    fun searchSentencesList(query: String): PagingSource<Int, PoemSentenceEntity>

    @Query("select id from poem_sentences where id > :id and content like :query order by id asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int

    @Query("select id from poem_sentences where id < :id and content like :query order by id desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int

    @Query("select p.*,c.collected_at from poem_sentence_collections c join poem_sentences p on c.id = p.id  order by c.collected_at desc")
    fun collections(): PagingSource<Int, PoemSentenceWithBookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: PoemSentenceCollectionEntity)

    @Query("delete from poem_sentence_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from poem_sentence_collections where id = :id")
    fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?>

    @Query("select id from poem_sentence_collections where collected_at < :collectedAt order by collected_at desc limit 1")
    fun getCollectionNextId(collectedAt: Long): Flow<Int?>

    @Query("select id from poem_sentence_collections where collected_at > :collectedAt order by collected_at asc limit 1")
    fun getCollectionPrevId(collectedAt: Long): Flow<Int?>
}