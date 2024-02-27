package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseKnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChineseKnowledgeEntity)

    @Query("select * from chinese_knowledge where id = :id limit 1")
    fun getChineseKnowledge(id: Int): Flow<ChineseKnowledgeEntity>

    @Query("select id from chinese_knowledge where id <:id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select id from chinese_knowledge where id > :id order by id asc limit 1")
    fun getNestId(id: Int): Flow<Int?>

    @Query("select * from chinese_knowledge where content like :query")
    fun getSearchChineseKnowledgeList(query: String): Flow<List<ChineseKnowledgeEntity>>

    @Query("delete from chinese_knowledge")
    suspend fun clear()
}