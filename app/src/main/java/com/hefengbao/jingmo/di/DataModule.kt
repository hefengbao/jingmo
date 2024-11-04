/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.di

import com.hefengbao.jingmo.data.repository.LinksRepository
import com.hefengbao.jingmo.data.repository.LinksRepositoryImpl
import com.hefengbao.jingmo.data.repository.china.WorldCultureHeritageRepository
import com.hefengbao.jingmo.data.repository.china.WorldCultureHeritageRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepository
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepository
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepository
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.LyricRepository
import com.hefengbao.jingmo.data.repository.chinese.LyricRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepository
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.RiddleRepository
import com.hefengbao.jingmo.data.repository.chinese.RiddleRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.TongueTwisterRepository
import com.hefengbao.jingmo.data.repository.chinese.TongueTwisterRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.PeopleRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.PeopleRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepositoryImpl
import com.hefengbao.jingmo.data.repository.settings.HomeItemRepository
import com.hefengbao.jingmo.data.repository.settings.HomeItemRepositoryImpl
import com.hefengbao.jingmo.data.repository.settings.ImportRepository
import com.hefengbao.jingmo.data.repository.settings.ImportRepositoryImpl
import com.hefengbao.jingmo.data.repository.settings.NetworkDatasourceRepository
import com.hefengbao.jingmo.data.repository.settings.NetworkDatasourceRepositoryImpl
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.settings.ThemeRepository
import com.hefengbao.jingmo.data.repository.settings.ThemeRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.SolarTermsRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.SolarTermsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsChinaWorldCultureHeritageRepository(
        repository: WorldCultureHeritageRepositoryImpl
    ): WorldCultureHeritageRepository

    @Binds
    fun bindsChineseAntitheticalCoupletRepository(
        repository: AntitheticalCoupletRepositoryImpl
    ): AntitheticalCoupletRepository

    @Binds
    fun bindsChineseCharacterRepository(
        repository: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    fun bindsChineseColorRepository(
        chineseColorRepository: ColorRepositoryImpl
    ): ColorRepository

    @Binds
    fun bindsChineseExpressionRepository(
        chineseExpressionRepositoryImpl: ExpressionRepositoryImpl
    ): ExpressionRepository

    @Binds
    fun bindsChineseQuoteRepository(
        repository: QuoteRepositoryImpl
    ): QuoteRepository

    @Binds
    fun bindsChineseKnowledgeRepository(
        chineseKnowledgeRepositoryImpl: KnowledgeRepositoryImpl
    ): KnowledgeRepository

    @Binds
    fun bindsChineseWisecrackRepository(
        chineseCrackRepository: WisecrackRepositoryImpl
    ): WisecrackRepository

    @Binds
    fun bindsClassicPoemRepository(
        poemRepository: ClassicPoemRepositoryImpl
    ): ClassicPoemRepository

    @Binds
    fun bindsFestivalRepository(
        festivalRepositoryImpl: FestivalRepositoryImpl
    ): FestivalRepository

    @Binds
    fun bindsHomeItemRepository(
        homeItemRepositoryImpl: HomeItemRepositoryImpl
    ): HomeItemRepository

    @Binds
    fun bindsIdiomRepository(
        idiomRepository: IdiomRepositoryImpl
    ): IdiomRepository

    @Binds
    fun bindsImportRepository(
        importRepositoryImpl: ImportRepositoryImpl
    ): ImportRepository

    @Binds
    fun bindsLinksRepository(
        linksRepositoryImpl: LinksRepositoryImpl
    ): LinksRepository

    @Binds
    fun bindsLyricRepository(
        lyricRepositoryImpl: LyricRepositoryImpl
    ): LyricRepository

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
        poemSentenceRepository: SentenceRepositoryImpl
    ): SentenceRepository

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
    fun bindsClassicalLiteratureWritingRepository(
        writingRepositoryImpl: WritingRepositoryImpl
    ): WritingRepository

    @Binds
    fun bindsChineseProverbRepository(
        proverbRepository: ProverbRepositoryImpl
    ): ProverbRepository

    @Binds
    fun bindsThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository
}