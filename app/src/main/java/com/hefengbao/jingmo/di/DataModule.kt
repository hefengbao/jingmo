package com.hefengbao.jingmo.di

import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.ChineseColorRepositoryImpl
import com.hefengbao.jingmo.data.repository.ChineseCrackRepositoryImpl
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.IdiomRepositoryImpl
import com.hefengbao.jingmo.data.repository.LinksRepository
import com.hefengbao.jingmo.data.repository.LinksRepositoryImpl
import com.hefengbao.jingmo.data.repository.PoemRepository
import com.hefengbao.jingmo.data.repository.PoemRepositoryImpl
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PoemSentenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.SyncRepository
import com.hefengbao.jingmo.data.repository.SyncRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsSyncRepository(
        syncRepository: SyncRepositoryImpl
    ): SyncRepository

    @Binds
    fun bindsPreferenceRepository(
        preferenceRepository: PreferenceRepositoryImpl
    ): PreferenceRepository

    @Binds
    fun bindsPoemRepository(
        poemRepository: PoemRepositoryImpl
    ): PoemRepository

    @Binds
    fun bindsPoemSentenceRepository(
        poemSentenceRepository: PoemSentenceRepositoryImpl
    ): PoemSentenceRepository

    @Binds
    fun bindsChineseWisecrackRepository(
        chineseCrackRepository: ChineseCrackRepositoryImpl
    ): ChineseWisecrackRepository

    @Binds
    fun bindsIdiomRepository(
        idiomRepository: IdiomRepositoryImpl
    ): IdiomRepository

    @Binds
    fun bindsChineseColorRepository(
        chineseColorRepository: ChineseColorRepositoryImpl
    ): ChineseColorRepository

    @Binds
    fun bindsLinksRepository(
        linksRepositoryImpl: LinksRepositoryImpl
    ): LinksRepository
}