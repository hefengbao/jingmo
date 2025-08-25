/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.china

import com.hefengbao.jingmo.data.database.entity.china.WorldCulturalHeritageEntity
import kotlinx.coroutines.flow.Flow

interface WorldCultureHeritageRepository {
    fun get(id: Int): Flow<WorldCulturalHeritageEntity?>
    fun list(): Flow<List<WorldCulturalHeritageEntity>>
}