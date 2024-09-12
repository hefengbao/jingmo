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
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseDictionaryDao {
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

    @Query("select count(d.id) from dictionary d")
    fun total(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity)

    @Query("delete from dictionary_pinyin")
    suspend fun clearDictionaryPinyin()

    @Query("delete from dictionary")
    suspend fun clear()
}