package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DictionaryEntity)

    @Query("select * from dictionary where id = :id limit 1")
    fun get(id: Int): Flow<DictionaryEntity>

    @Query("select * from dictionary where char = :character")
    fun get(character: String): Flow<List<DictionaryEntity>>

    @Query("select * from dictionary where radical = :radical")
    fun getByRadical(radical: String): Flow<List<DictionaryEntity>>

    @Query("select * from dictionary where stroke = :stroke")
    fun getByStroke(stroke: Int): Flow<List<DictionaryEntity>>

    @Query("select d.* from dictionary d where d.id in (select p.dictionary_id from dictionary_pinyin p where p.pinyin = :pinyin)")
    fun getByPinyin(pinyin: String): Flow<List<DictionaryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity)

    @Query("delete from dictionary_pinyin")
    suspend fun clearDictionaryPinyin()

    @Query("select count(d.id) from dictionary d")
    fun total(): Flow<Int>
}