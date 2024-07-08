/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.HomeItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun setClassicPoem(checked: Boolean)
    suspend fun setWriting(checked: Boolean)
    suspend fun setPoemSentence(checked: Boolean)
    suspend fun setIdiom(checked: Boolean)
    suspend fun setChineseWisecrack(checked: Boolean)
    suspend fun setTongueTwister(checked: Boolean)
    suspend fun setFestival(checked: Boolean)
    suspend fun setSolarTerm(checked: Boolean)
    suspend fun setChineseKnowledge(checked: Boolean)
    suspend fun setPeople(checked: Boolean)
    suspend fun setChineseColor(checked: Boolean)
    suspend fun setChineseCharacter(checked: Boolean)
    suspend fun setChineseExpression(checked: Boolean)
    fun getHomeItem(): Flow<HomeItem>
}