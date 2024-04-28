package com.hefengbao.jingmo.di

import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.ChineseColorRepositoryImpl
import com.hefengbao.jingmo.data.repository.ChineseKnowledgeRepository
import com.hefengbao.jingmo.data.repository.ChineseKnowledgeRepositoryImpl
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepositoryImpl
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import com.hefengbao.jingmo.data.repository.ClassicPoemRepositoryImpl
import com.hefengbao.jingmo.data.repository.FestivalRepository
import com.hefengbao.jingmo.data.repository.FestivalRepositoryImpl
import com.hefengbao.jingmo.data.repository.HomeRepository
import com.hefengbao.jingmo.data.repository.HomeRepositoryImpl
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.IdiomRepositoryImpl
import com.hefengbao.jingmo.data.repository.ImportRepository
import com.hefengbao.jingmo.data.repository.ImportRepositoryImpl
import com.hefengbao.jingmo.data.repository.LinksRepository
import com.hefengbao.jingmo.data.repository.LinksRepositoryImpl
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepository
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepositoryImpl
import com.hefengbao.jingmo.data.repository.PeopleRepository
import com.hefengbao.jingmo.data.repository.PeopleRepositoryImpl
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PoemSentenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.RiddleRepository
import com.hefengbao.jingmo.data.repository.RiddleRepositoryImpl
import com.hefengbao.jingmo.data.repository.SolarTermsRepository
import com.hefengbao.jingmo.data.repository.SolarTermsRepositoryImpl
import com.hefengbao.jingmo.data.repository.TongueTwisterRepository
import com.hefengbao.jingmo.data.repository.TongueTwisterRepositoryImpl
import com.hefengbao.jingmo.data.repository.WritingRepository
import com.hefengbao.jingmo.data.repository.WritingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsIdiomRepository(
        idiomRepository: IdiomRepositoryImpl
    ): IdiomRepository

    @Binds
    fun bindsChineseColorRepository(
        chineseColorRepository: ChineseColorRepositoryImpl
    ): ChineseColorRepository

    @Binds
    fun bindsChineseKnowledgeRepository(
        chineseKnowledgeRepositoryImpl: ChineseKnowledgeRepositoryImpl
    ): ChineseKnowledgeRepository

    @Binds
    fun bindsChineseWisecrackRepository(
        chineseCrackRepository: ChineseWisecrackRepositoryImpl
    ): ChineseWisecrackRepository

    @Binds
    fun bindsClassicPoemRepository(
        poemRepository: ClassicPoemRepositoryImpl
    ): ClassicPoemRepository

    @Binds
    fun bindsFestivalRepository(
        festivalRepositoryImpl: FestivalRepositoryImpl
    ): FestivalRepository

    @Binds
    fun bindsHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    fun bindsImportRepository(
        importRepositoryImpl: ImportRepositoryImpl
    ): ImportRepository

    @Binds
    fun bindsLinksRepository(
        linksRepositoryImpl: LinksRepositoryImpl
    ): LinksRepository

    @Binds
    fun bindsNetworkDatasourceRepository(
        networkDatasourceRepositoryImpl: NetworkDatasourceRepositoryImpl
    ): NetworkDatasourceRepository

    @Binds
    fun bindsPeopleRepository(
        peopleRepositoryImpl: PeopleRepositoryImpl
    ): PeopleRepository

    @Binds
    fun bindsPoemSentenceRepository(
        poemSentenceRepository: PoemSentenceRepositoryImpl
    ): PoemSentenceRepository

    @Binds
    fun bindsPreferenceRepository(
        preferenceRepository: PreferenceRepositoryImpl
    ): PreferenceRepository

    @Binds
    fun bindsRiddleRepository(
        riddleRepositoryImpl: RiddleRepositoryImpl
    ): RiddleRepository

    @Binds
    fun bindsSolarTermsRepository(
        solarTermsRepositoryImpl: SolarTermsRepositoryImpl
    ): SolarTermsRepository

    @Binds
    fun bindsTongueTwisterRepository(
        tongueTwisterRepositoryImpl: TongueTwisterRepositoryImpl
    ): TongueTwisterRepository

    @Binds
    fun bindsWritingRepository(
        writingRepositoryImpl: WritingRepositoryImpl
    ): WritingRepository
}