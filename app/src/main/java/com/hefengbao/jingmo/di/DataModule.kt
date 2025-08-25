/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.di

import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.BookmarkRepositoryImpl
import com.hefengbao.jingmo.data.repository.LinksRepository
import com.hefengbao.jingmo.data.repository.LinksRepositoryImpl
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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.hefengbao.jingmo.data.repository.china.WorldCultureHeritageRepository as ChinaWorldCultureHeritageRepository
import com.hefengbao.jingmo.data.repository.china.WorldCultureHeritageRepositoryImpl as ChinaWorldCultureHeritageRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepository as ChineseAntitheticalCoupletRepository
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepositoryImpl as ChineseAntitheticalCoupletRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository as ChineseCharacterRepository
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepositoryImpl as ChineseCharacterRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepository as ChineseExpressionRepository
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepositoryImpl as ChineseExpressionRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepository as ChineseIdiomRepository
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepositoryImpl as ChineseIdiomRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository as ChineseKnowledgeRepository
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepositoryImpl as ChineseKnowledgeRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.LyricRepository as ChineseLyricRepository
import com.hefengbao.jingmo.data.repository.chinese.LyricRepositoryImpl as ChineseLyricRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository as ChineseModernPoetryRepository
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepositoryImpl as ChineseModernPoetryRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepository as ChineseProverbRepository
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepositoryImpl as ChineseProverbRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository as ChineseQuoteRepository
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepositoryImpl as ChineseQuoteRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.RiddleRepository as ChineseRiddleRepository
import com.hefengbao.jingmo.data.repository.chinese.RiddleRepositoryImpl as ChineseRiddleRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.TongueTwisterRepository as ChineseTongueTwisterRepository
import com.hefengbao.jingmo.data.repository.chinese.TongueTwisterRepositoryImpl as ChineseTongueTwisterRepositoryImpl
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository as ChineseWisecrackRepository
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepositoryImpl as ChineseWisecrackRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository as ClassicalLLiteratureClassicPoemRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepositoryImpl as ClassicalLLiteratureClassicPoemRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.PeopleRepository as ClassicalLLiteraturePeopleRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.PeopleRepositoryImpl as ClassicalLLiteraturePeopleRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepository as ClassicalLLiteratureSentenceRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepositoryImpl as ClassicalLLiteratureSentenceRepositoryImpl
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository as ClassicalLLiteratureWritingRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepositoryImpl as ClassicalLLiteratureWritingRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository as TraditionalCultureColorRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepositoryImpl as TraditionalCultureColorRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepository as TraditionalCultureFestivalRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepositoryImpl as TraditionalCultureFestivalRepositoryImpl
import com.hefengbao.jingmo.data.repository.traditionalculture.SolarTermsRepository as TraditionalCultureSolarTermsRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.SolarTermsRepositoryImpl as TraditionalCultureSolarTermsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsBookmarkRepository(
        repository: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    fun bindsChinaWorldCultureHeritageRepository(
        repository: ChinaWorldCultureHeritageRepositoryImpl
    ): ChinaWorldCultureHeritageRepository

    @Binds
    fun bindsChineseAntitheticalCoupletRepository(
        repository: ChineseAntitheticalCoupletRepositoryImpl
    ): ChineseAntitheticalCoupletRepository

    @Binds
    fun bindsChineseCharacterRepository(
        repository: ChineseCharacterRepositoryImpl
    ): ChineseCharacterRepository

    @Binds
    fun bindsChineseExpressionRepository(
        repository: ChineseExpressionRepositoryImpl
    ): ChineseExpressionRepository

    @Binds
    fun bindsChineseModernPoetryRepository(
        repository: ChineseModernPoetryRepositoryImpl
    ): ChineseModernPoetryRepository

    @Binds
    fun bindsChineseQuoteRepository(
        repository: ChineseQuoteRepositoryImpl
    ): ChineseQuoteRepository

    @Binds
    fun bindsChineseKnowledgeRepository(
        repository: ChineseKnowledgeRepositoryImpl
    ): ChineseKnowledgeRepository

    @Binds
    fun bindsChineseWisecrackRepository(
        repository: ChineseWisecrackRepositoryImpl
    ): ChineseWisecrackRepository

    @Binds
    fun bindsChineseIdiomRepository(
        repository: ChineseIdiomRepositoryImpl
    ): ChineseIdiomRepository

    @Binds
    fun bindsChineseLyricRepository(
        repository: ChineseLyricRepositoryImpl
    ): ChineseLyricRepository

    @Binds
    fun bindsChineseRiddleRepository(
        repository: ChineseRiddleRepositoryImpl
    ): ChineseRiddleRepository

    @Binds
    fun bindsChineseTongueTwisterRepository(
        repository: ChineseTongueTwisterRepositoryImpl
    ): ChineseTongueTwisterRepository

    @Binds
    fun bindsChineseProverbRepository(
        repository: ChineseProverbRepositoryImpl
    ): ChineseProverbRepository

    @Binds
    fun bindsClassicalLLiteratureClassicPoemRepository(
        repository: ClassicalLLiteratureClassicPoemRepositoryImpl
    ): ClassicalLLiteratureClassicPoemRepository

    @Binds
    fun bindsClassicalLLiteraturePeopleRepository(
        repository: ClassicalLLiteraturePeopleRepositoryImpl
    ): ClassicalLLiteraturePeopleRepository

    @Binds
    fun bindsClassicalLLiteratureSentenceRepository(
        repository: ClassicalLLiteratureSentenceRepositoryImpl
    ): ClassicalLLiteratureSentenceRepository

    @Binds
    fun bindsClassicalLiteratureWritingRepository(
        repository: ClassicalLLiteratureWritingRepositoryImpl
    ): ClassicalLLiteratureWritingRepository

    @Binds
    fun bindsTraditionalCultureColorRepository(
        repository: TraditionalCultureColorRepositoryImpl
    ): TraditionalCultureColorRepository


    @Binds
    fun bindsTraditionalCultureFestivalRepository(
        repository: TraditionalCultureFestivalRepositoryImpl
    ): TraditionalCultureFestivalRepository

    @Binds
    fun bindsTraditionalCultureSolarTermsRepository(
        repository: TraditionalCultureSolarTermsRepositoryImpl
    ): TraditionalCultureSolarTermsRepository

    @Binds
    fun bindsHomeItemRepository(
        repository: HomeItemRepositoryImpl
    ): HomeItemRepository

    @Binds
    fun bindsImportRepository(
        repository: ImportRepositoryImpl
    ): ImportRepository

    @Binds
    fun bindsLinksRepository(
        repository: LinksRepositoryImpl
    ): LinksRepository

    @Binds
    fun bindsNetworkDatasourceRepository(
        repository: NetworkDatasourceRepositoryImpl
    ): NetworkDatasourceRepository

    @Binds
    fun bindsPreferenceRepository(
        repository: PreferenceRepositoryImpl
    ): PreferenceRepository

    @Binds
    fun bindsThemeRepository(
        repository: ThemeRepositoryImpl
    ): ThemeRepository
}