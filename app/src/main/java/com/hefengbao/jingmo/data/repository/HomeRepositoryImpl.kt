package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.datastore.HomePreference
import com.hefengbao.jingmo.data.model.HomeItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homePreference: HomePreference
) : HomeRepository {
    override suspend fun setClassicPoem(checked: Boolean) = homePreference.setClassicPoem(checked)

    override suspend fun setWriting(checked: Boolean) = homePreference.setWriting(checked)

    override suspend fun setPoemSentence(checked: Boolean) = homePreference.setPoemSentence(checked)

    override suspend fun setIdiom(checked: Boolean) = homePreference.setIdiom(checked)

    override suspend fun setChineseWisecrack(checked: Boolean) =
        homePreference.setChineseWisecrack(checked)

    override suspend fun setTongueTwister(checked: Boolean) =
        homePreference.setTongueTwister(checked)

    override suspend fun setFestival(checked: Boolean) = homePreference.setFestival(checked)

    override suspend fun setSolarTerm(checked: Boolean) = homePreference.setSolarTerm(checked)

    override suspend fun setChineseKnowledge(checked: Boolean) =
        homePreference.setChineseKnowledge(checked)

    override suspend fun setPeople(checked: Boolean) = homePreference.setPeople(checked)

    override suspend fun setChineseColor(checked: Boolean) = homePreference.setChineseColor(checked)

    override suspend fun setChineseCharacter(checked: Boolean) =
        homePreference.setChineseCharacter(checked)

    override suspend fun setChineseExpression(checked: Boolean) =
        homePreference.setChineseExpression(checked)

    override fun getHomeItem(): Flow<HomeItem> = homePreference.homeItem
}