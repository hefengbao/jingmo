package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseWisecrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChineseWisecrackEntity)

    @Query("select w.*,c.collected_at from chinese_wisecracks w left join chinese_wisecrack_collections c on w.id = c.id where w.id = :id")
    fun getChineseWisecrack(id: Int): Flow<ChineseWisecrackWithBookmark>

    @Query("select id from chinese_wisecracks where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_wisecracks where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_wisecracks where riddle like :query or answer like :query")
    fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>>

    @Query("select w.* from chinese_wisecrack_collections c join chinese_wisecracks w on c.id = w.id order by c.collected_at desc")
    fun collections(): PagingSource<Int, ChineseWisecrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: ChineseWisecrackCollectionEntity)

    @Query("delete from chinese_wisecrack_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from chinese_wisecrack_collections where id = :id")
    fun isCollect(id: Int): Flow<ChineseWisecrackCollectionEntity?>

    @Query("select count(*) from chinese_wisecracks")
    fun total(): Flow<Int>
}