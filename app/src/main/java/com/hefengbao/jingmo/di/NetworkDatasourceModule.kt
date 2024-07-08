/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.di

import com.hefengbao.jingmo.data.network.Network
import com.hefengbao.jingmo.data.network.retrofit.NetworkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDatasourceModule {
    @Binds
    fun bindsNetwork(
        networkImpl: NetworkImpl
    ): Network
}