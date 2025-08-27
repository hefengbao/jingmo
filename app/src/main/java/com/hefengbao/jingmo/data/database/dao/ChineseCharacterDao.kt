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
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseCharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CharacterEntity)

    @Query("select * from chinese_character where id = (select d.id from chinese_character d order by random() limit 1) limit 1")
    fun random(): Flow<CharacterEntity?>

    @Query("select * from chinese_character where id = :id limit 1")
    fun get(id: Int): Flow<CharacterEntity?>

    @Query("select * from chinese_character where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<CharacterEntity>>

    @Query("select * from chinese_character where character = :character")
    fun get(character: String): Flow<List<CharacterEntity>>

    @Query("select * from chinese_character where radical = :radical")
    fun getByRadical(radical: String): Flow<List<CharacterEntity>>

    @Query("select * from chinese_character where stroke = :stroke")
    fun getByStroke(stroke: Int): Flow<List<CharacterEntity>>

    @Query("select * from chinese_character where pinyin = :pinyin")
    fun getByPinyin(pinyin: String): Flow<List<CharacterEntity>>

    @Query("select c.* from bookmarks b join chinese_character c on b.bookmarkable_id = c.id and b.bookmarkable_model = 'chinese_character' order by b.id desc")
    fun bookmarks(): PagingSource<Int, CharacterEntity>

    @Query("select count(id) from chinese_character")
    fun total(): Flow<Int>

    @Query("delete from chinese_character")
    suspend fun clear()
}