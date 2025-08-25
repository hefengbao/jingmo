/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network.local

import com.hefengbao.jingmo.common.network.AppDispatchers
import com.hefengbao.jingmo.common.network.Dispatcher
import com.hefengbao.jingmo.data.model.chinese.character.Syllable
import com.hefengbao.jingmo.data.network.JvmUnitTestFakeAssetManager
import com.hefengbao.jingmo.data.network.LocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: LocalAssetManager = JvmUnitTestFakeAssetManager,
) : LocalDataSource {
    override suspend fun getSyllables(): List<Syllable> = getDataFromJsonFile("syllables.json")

    /**
     * Get data from the given JSON [fileName].
     */
    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> getDataFromJsonFile(fileName: String): List<T> =
        withContext(ioDispatcher) {
            assets.open(fileName).use { inputStream ->
                networkJson.decodeFromStream(inputStream)
            }
        }
}