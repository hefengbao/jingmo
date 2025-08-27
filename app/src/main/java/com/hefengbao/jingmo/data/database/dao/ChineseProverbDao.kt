package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseProverbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProverbEntity)

    @Query("select * from chinese_proverb where id = :id limit 1")
    fun get(id: Int): Flow<ProverbEntity?>

    @Query("select * from chinese_proverb where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<ProverbEntity>>

    @Query("select p.* from chinese_proverb p where p.id = (select id from chinese_proverb order by random() limit 1) limit 1")
    fun random(): Flow<ProverbEntity?>

    @Query("select id from chinese_proverb where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_proverb where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_proverb join chinese_proverb_fts on chinese_proverb_fts.rowid = chinese_proverb.id where chinese_proverb_fts match :query")
    fun search(query: String): Flow<List<ProverbEntity>>

    @Query("select p.* from bookmarks b join chinese_proverb p on b.bookmarkable_id = p.id and b.bookmarkable_model = 'chinese_proverb' order by b.id desc")
    fun bookmarks(): PagingSource<Int, ProverbEntity>

    @Query("select count(*) from chinese_proverb")
    fun total(): Flow<Int>

    @Query("delete from chinese_proverb")
    suspend fun clear()
}