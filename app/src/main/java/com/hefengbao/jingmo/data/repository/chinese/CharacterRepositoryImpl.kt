/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseCharacterDao
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import com.hefengbao.jingmo.data.model.chinese.character.Radical
import com.hefengbao.jingmo.data.model.chinese.character.Stroke
import com.hefengbao.jingmo.data.model.chinese.character.Syllable
import com.hefengbao.jingmo.data.network.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: ChineseCharacterDao,
    private val localDataSource: LocalDataSource
) : CharacterRepository {

    override suspend fun syllables(): List<Syllable> = localDataSource.getSyllables()

    override fun radicals(): List<Radical> = listOf(
        Radical(
            stroke = "一画",
            radicals = listOf("一", "丨", "丿", "丶", "乛", "㇕", "⺄", "乚", "亅")
        ),
        Radical(
            stroke = "二画",
            radicals = listOf(
                "㔾",
                "廴",
                "讠",
                "阝",
                "又",
                "厶",
                "厂",
                "卩",
                "卜",
                "十",
                "匚",
                "匕",
                "二",
                "勹",
                "力",
                "刂",
                "刀",
                "凵",
                "几",
                "冫",
                "冖",
                "冂",
                "八",
                "亻",
                "儿",
                "入",
                "亠",
                "人",
                "丷"
            )
        ),
        Radical(
            stroke = "三画",
            radicals = listOf(
                "扌",
                "马",
                "饣",
                "飞",
                "门",
                "辶",
                "艹",
                "犭",
                "纟",
                "氵",
                "忄",
                "川",
                "彳",
                "彡",
                "弓",
                "彐",
                "彑",
                "弋",
                "廾",
                "广",
                "幺",
                "干",
                "巾",
                "巳",
                "已",
                "己",
                "工",
                "巛",
                "屮",
                "山",
                "尸",
                "小",
                "兀",
                "尢",
                "宀",
                "寸",
                "子",
                "女",
                "大",
                "夕",
                "夂",
                "士",
                "口",
                "囗",
                "土",
                "丬"
            )
        ),
        Radical(
            stroke = "四画",
            listOf(
                "风",
                "韦",
                "车",
                "贝",
                "见",
                "肀",
                "罓",
                "耂",
                "礻",
                "瓦",
                "王",
                "犬",
                "牛",
                "牙",
                "片",
                "爿",
                "爻",
                "父",
                "爪",
                "爫",
                "灬",
                "厄",
                "火",
                "水",
                "气",
                "氏",
                "毛",
                "比",
                "毋",
                "殳",
                "歹",
                "止",
                "朩",
                "欠",
                "木",
                "月",
                "日",
                "旡",
                "方",
                "斤",
                "斗",
                "文",
                "攵",
                "攴",
                "支",
                "手",
                "户",
                "戈",
                "心",
                "尣",
                "⺼",
                "曰",
                "龵",
                "无"
            )
        ),
        Radical(
            stroke = "五画",
            listOf(
                "龙",
                "鸟",
                "钅",
                "衤",
                "罒",
                "立",
                "禾",
                "穴",
                "示",
                "石",
                "矢",
                "矛",
                "目",
                "皿",
                "皮",
                "白",
                "癶",
                "疒",
                "疋",
                "田",
                "用",
                "玄",
                "生",
                "四",
                "巨",
                "甘",
                "瓜",
                "玉",
                "氺",
                "母",
                "业",
                "歺",
                "⺻"
            )
        ),
        Radical(
            stroke = "六画",
            listOf(
                "齐",
                "页",
                "西",
                "衣",
                "血",
                "虫",
                "虍",
                "艸",
                "色",
                "艮",
                "舟",
                "舌",
                "臼",
                "至",
                "自",
                "臣",
                "耳",
                "耒",
                "而",
                "行",
                "羽",
                "羊",
                "缶",
                "糹",
                "糸",
                "网",
                "老",
                "竹",
                "米",
                "先",
                "覀",
                "舛"
            )
        ),
        Radical(
            stroke = "七画",
            listOf(
                "镸",
                "龟",
                "麦",
                "里",
                "釆",
                "酉",
                "邑",
                "辵",
                "辰",
                "辛",
                "車",
                "身",
                "足",
                "走",
                "赤",
                "貝",
                "豸",
                "豕",
                "豆",
                "谷",
                "言",
                "克",
                "角",
                "見",
                "卤"
            )
        ),
        Radical(
            stroke = "八画",
            listOf(
                "𨸏",
                "龺",
                "齿",
                "黾",
                "鱼",
                "飠",
                "靑",
                "青",
                "非",
                "雨",
                "隹",
                "采",
                "隶",
                "阜",
                "門",
                "長",
                "金",
                "釒"
            )
        ),
        Radical(
            stroke = "九画",
            listOf("鬼", "骨", "香", "首", "食", "風", "頁", "音", "韭", "韋", "革", "面", "飛")
        ),
        Radical(
            stroke = "十画",
            listOf("鬲", "鬥", "髟", "高", "馬")
        ),
        Radical(
            stroke = "十一画",
            listOf("黄", "麻", "麥", "鹿", "鹵", "鳥", "魚")
        ),
        Radical(
            stroke = "十二画",
            listOf("鼎", "黑", "黍", "黹")
        ),
        Radical(
            stroke = "十三画",
            listOf("鼠", "黽", "鼓")
        ),
        Radical(
            stroke = "十四画",
            listOf("齊", "鼻")
        ),
        Radical(
            stroke = "十五画",
            listOf("齒")
        ),
        Radical(
            stroke = "十六画",
            listOf("龜", "龍")
        ),
        Radical(
            stroke = "十七画",
            listOf("龠")
        ),
    )

    override fun strokes(): List<Stroke> = listOf(
        Stroke("一画", 1),
        Stroke("二画", 2),
        Stroke("三画", 3),
        Stroke("四画", 4),
        Stroke("五画", 5),
        Stroke("六画", 6),
        Stroke("七画", 7),
        Stroke("八画", 8),
        Stroke("九画", 9),
        Stroke("十画", 10),
        Stroke("十一画", 11),
        Stroke("十二画", 12),
        Stroke("十三画", 13),
        Stroke("十四画", 14),
        Stroke("十五画", 15),
        Stroke("十六画", 16),
        Stroke("十七画", 17),
        Stroke("十八画", 18),
        Stroke("十九画", 19),
        Stroke("二十画", 20),
        Stroke("二十一画", 21),
        Stroke("二十二画", 22),
        Stroke("二十三画", 23),
        Stroke("二十四画", 24),
        Stroke("二十五画", 25),
        Stroke("二十六画", 26),
        Stroke("二十七画", 27),
        Stroke("二十八画", 28),
        Stroke("二十九画", 29),
        Stroke("三十画", 30),
        Stroke("三十一画", 31),
        Stroke("三十二画", 32),
        Stroke("三十三画", 33),
        Stroke("三十四画", 34),
        Stroke("三十五画", 35),
        Stroke("三十六画", 36),
        Stroke("三十九画", 39),
        Stroke("五十一画", 51),
    )

    override fun getRandom(): Flow<CharacterEntity?> = dao.random()

    override fun get(id: Int): Flow<CharacterEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<CharacterEntity>> = dao.get(ids)

    override fun search(character: String): Flow<List<CharacterEntity>> = dao.get(character)

    override fun searchByPinyin(pinyin: String): Flow<List<CharacterEntity>> =
        dao.getByPinyin(pinyin)

    override fun searchByRadical(radical: String): Flow<List<CharacterEntity>> =
        dao.getByRadical(radical)

    override fun searchByStroke(stroke: Int): Flow<List<CharacterEntity>> = dao.getByStroke(stroke)

    override fun bookmarks(): Flow<PagingData<CharacterEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow
}