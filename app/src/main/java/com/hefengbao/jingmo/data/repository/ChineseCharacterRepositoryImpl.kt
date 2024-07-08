/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.DictionaryDao
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.model.CharacterPinyin
import com.hefengbao.jingmo.data.model.CharacterRadical
import com.hefengbao.jingmo.data.model.CharacterStroke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChineseCharacterRepositoryImpl @Inject constructor(
    private val dao: DictionaryDao
) : ChineseCharacterRepository {

    override fun pinyins(): List<CharacterPinyin> = listOf(
        CharacterPinyin(
            alphabet = "A",
            pinyin = listOf("a", "ai", "an", "ang", "ao")
        ),
        CharacterPinyin(
            alphabet = "B",
            pinyin = listOf(
                "ba",
                "bai",
                "ban",
                "bang",
                "bao",
                "bei",
                "ben",
                "beng",
                "bi",
                "bian",
                "biao",
                "bie",
                "bin",
                "bing",
                "bo",
                "bu"
            )
        ),
        CharacterPinyin(
            alphabet = "C",
            pinyin = listOf(
                "ca",
                "cai",
                "can",
                "cang",
                "cao",
                "ce",
                "cen",
                "ceng",
                "cha",
                "chai",
                "chan",
                "chang",
                "chao",
                "che",
                "chen",
                "cheng",
                "chi",
                "chong",
                "chou",
                "chu",
                "chua",
                "chuai",
                "chuan",
                "chuang",
                "chui",
                "chun",
                "chuo",
                "ci",
                "cong",
                "cou",
                "cu",
                "cuan",
                "cui",
                "cun",
                "cuo"
            )
        ),
        CharacterPinyin(
            alphabet = "D",
            pinyin = listOf(
                "da",
                "dai",
                "dan",
                "dang",
                "dao",
                "de",
                "dei",
                "den",
                "deng",
                "di",
                "dia",
                "dian",
                "diao",
                "die",
                "ding",
                "diu",
                "dong",
                "dou",
                "du",
                "duan",
                "dui",
                "dun",
                "duo"
            )
        ),
        CharacterPinyin(
            alphabet = "E",
            pinyin = listOf("e", "ei", "en", "eng", "er")
        ),
        CharacterPinyin(
            alphabet = "F",
            pinyin = listOf("fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu")
        ),
        CharacterPinyin(
            alphabet = "G",
            pinyin = listOf(
                "ga",
                "gai",
                "gan",
                "gang",
                "gao",
                "ge",
                "gei",
                "gen",
                "geng",
                "gong",
                "gou",
                "gu",
                "gua",
                "guai",
                "guan",
                "guang",
                "gui",
                "gun",
                "guo"
            )
        ),
        CharacterPinyin(
            alphabet = "H",
            pinyin = listOf(
                "ha",
                "hai",
                "han",
                "hang",
                "hao",
                "he",
                "hei",
                "hen",
                "heng",
                "hong",
                "hou",
                "hu",
                "hua",
                "huai",
                "huan",
                "huang",
                "hui",
                "hun",
                "huo"
            )
        ),
        CharacterPinyin(
            alphabet = "J",
            pinyin = listOf(
                "ji",
                "jia",
                "jian",
                "jiang",
                "jiao",
                "jie",
                "jin",
                "jing",
                "jiong",
                "jiu",
                "ju",
                "juan",
                "jue",
                "jun"
            )
        ),
        CharacterPinyin(
            alphabet = "K",
            pinyin = listOf(
                "ka",
                "kai ",
                "kan ",
                "kang ",
                "kao ",
                "ke ",
                "ken ",
                "keng ",
                "kong ",
                "kou ",
                "ku ",
                "kua ",
                "kuai ",
                "kuan ",
                "kuang ",
                "kui ",
                "kun ",
                "kuo"
            )
        ),
        CharacterPinyin(
            alphabet = "L",
            pinyin = listOf(
                "la",
                "lai",
                "lan",
                "lang",
                "lao",
                "le",
                "lei",
                "leng",
                "li",
                "lia",
                "lian",
                "liang",
                "liao",
                "lie",
                "lin",
                "ling",
                "liu",
                "lo",
                "long",
                "lou",
                "lu",
                "luan",
                "lun",
                "luo",
                "lü",
                "lüe"
            )
        ),
        CharacterPinyin(
            alphabet = "M",
            pinyin = listOf(
                "ma",
                "mai",
                "man",
                "mang",
                "mao",
                "me",
                "mei",
                "men",
                "meng",
                "mi",
                "mian",
                "miao",
                "mie",
                "min",
                "ming",
                "miu",
                "mo",
                "mou",
                "mu"
            )
        ),
        CharacterPinyin(
            alphabet = "N",
            pinyin = listOf(
                "na",
                "nai",
                "nan",
                "nang",
                "nao",
                "ne",
                "nei",
                "nen",
                "neng",
                "ni",
                "nian",
                "niang",
                "niao",
                "nie",
                "nin",
                "ning",
                "niu",
                "nong",
                "nou",
                "nu",
                "nuan",
                "nun",
                "nuo",
                "nü",
                "nüe"
            )
        ),
        CharacterPinyin(
            alphabet = "O",
            pinyin = listOf("o", "ou")
        ),
        CharacterPinyin(
            alphabet = "P",
            pinyin = listOf(
                "pa",
                "pai",
                "pan",
                "pang",
                "pao",
                "pei",
                "pen",
                "peng",
                "pi",
                "pian",
                "piao",
                "pie",
                "pin",
                "ping",
                "po",
                "pou",
                "pu"
            )
        ),
        CharacterPinyin(
            alphabet = "Q",
            pinyin = listOf(
                "qi",
                "qia",
                "qian",
                "qiang",
                "qiao",
                "qie",
                "qin",
                "qing",
                "qiong",
                "qiu",
                "qu",
                "quan",
                "que",
                "qun"
            )
        ),
        CharacterPinyin(
            alphabet = "R",
            pinyin = listOf(
                "ran",
                "rang",
                "rao",
                "re",
                "ren",
                "reng",
                "ri",
                "rong",
                "rou",
                "ru",
                "ruan",
                "rui",
                "run",
                "ruo"
            )
        ),
        CharacterPinyin(
            alphabet = "S",
            pinyin = listOf(
                "sa",
                "sai",
                "san",
                "sang",
                "sao",
                "se",
                "sen",
                "seng",
                "sha",
                "shai",
                "shan",
                "shang",
                "shao",
                "she",
                "shen",
                "sheng",
                "shi",
                "shou",
                "shu",
                "shua",
                "shuai",
                "shuan",
                "shuang",
                "shui",
                "shun",
                "shuo",
                "si",
                "song",
                "sou",
                "su",
                "suan",
                "sui",
                "sun",
                "suo"
            )
        ),
        CharacterPinyin(
            alphabet = "T",
            pinyin = listOf(
                "ta",
                "tai",
                "tan",
                "tang",
                "tao",
                "te",
                "teng",
                "ti",
                "tian",
                "tiao",
                "tie",
                "ting",
                "tong",
                "tou",
                "tu",
                "tuan",
                "tui",
                "tun",
                "tuo"
            )
        ),
        CharacterPinyin(
            alphabet = "W",
            pinyin = listOf("wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu")
        ),
        CharacterPinyin(
            alphabet = "X",
            pinyin = listOf(
                "xi",
                "xia",
                "xian",
                "xiang",
                "xiao",
                "xie",
                "xin",
                "xing",
                "xiong",
                "xiu",
                "xu",
                "xuan",
                "xue",
                "xun"
            )
        ),
        CharacterPinyin(
            alphabet = "Y",
            pinyin = listOf(
                "ya",
                "yan",
                "yang",
                "yao",
                "ye",
                "yi",
                "yin",
                "ying",
                "yo",
                "yong",
                "you",
                "yu",
                "yuan",
                "yue",
                "yun"
            )
        ),
        CharacterPinyin(
            alphabet = "Z",
            pinyin = listOf(
                "za",
                "zai",
                "zan",
                "zang",
                "zao",
                "ze",
                "zei",
                "zen",
                "zeng",
                "zha",
                "zhai",
                "zhan",
                "zhang",
                "zhao",
                "zhe",
                "zhei",
                "zhen",
                "zheng",
                "zhi",
                "zhong",
                "zhou",
                "zhu",
                "zhua",
                "zhuai",
                "zhuan",
                "zhuang",
                "zhui",
                "zhun",
                "zhuo",
                "zi",
                "zong",
                "zou",
                "zu",
                "zuan",
                "zui",
                "zun",
                "zuo"
            )
        ),
    )

    override fun radicals(): List<CharacterRadical> = listOf(
        CharacterRadical(
            stroke = "一画",
            radicals = listOf("丨", "亅", "丿", "乛", "一", "乙", "乚", "丶")
        ),
        CharacterRadical(
            stroke = "二画",
            radicals = listOf(
                "十",
                "厂",
                "匚",
                "刂",
                "卜",
                "冂",
                "亻",
                "八",
                "人",
                "入",
                "勹",
                "儿",
                "匕",
                "几",
                "亠",
                "",
                "丷",
                "冖",
                "讠",
                "凵",
                "卩",
                "阝",
                "刀",
                "力",
                "又",
                "厶",
                "廴"
            )
        ),
        CharacterRadical(
            stroke = "三画",
            radicals = listOf(
                "干",
                "艹",
                "屮",
                "彳",
                "巛",
                "川",
                "辶",
                "寸",
                "大",
                "飞",
                "彑",
                "工",
                "弓",
                "廾",
                "广",
                "己",
                "彐",
                "巾",
                "口",
                "马",
                "门",
                "宀",
                "女",
                "犭",
                "山",
                "彡",
                "尸",
                "饣",
                "士",
                "扌",
                "氵",
                "纟",
                "巳",
                "土",
                "囗",
                "兀",
                "夕",
                "小",
                "忄",
                "幺",
                "弋",
                "尢",
                "夂",
                "子"
            )
        ),
        CharacterRadical(
            stroke = "四画",
            listOf(
                "贝",
                "比",
                "灬",
                "长",
                "车",
                "歹",
                "斗",
                "厄",
                "方",
                "风",
                "父",
                "戈",
                "卝",
                "户",
                "火",
                "旡",
                "见",
                "斤",
                "耂",
                "毛",
                "木",
                "牛",
                "牜",
                "爿",
                "片",
                "攴",
                "攵",
                "气",
                "欠",
                "犬",
                "日",
                "氏",
                "礻",
                "手",
                "殳",
                "水",
                "瓦",
                "王",
                "韦",
                "文",
                "无",
                "毋",
                "心",
                "穴",
                "牙",
                "爻",
                "曰",
                "月",
                "爫",
                "支",
                "止",
                "爪",
                "车"
            )
        ),
        CharacterRadical(
            stroke = "五画",
            listOf(
                "白",
                "癶",
                "甘",
                "瓜",
                "禾",
                "钅",
                "立",
                "龙",
                "矛",
                "皿",
                "母",
                "目",
                "疒",
                "鸟",
                "皮",
                "生",
                "石",
                "矢",
                "示",
                "罒",
                "田",
                "玄",
                "疋",
                "业",
                "衤",
                "用",
                "玉"
            )
        ),
        CharacterRadical(
            stroke = "六画",
            listOf(
                "臣",
                "虫",
                "而",
                "耳",
                "缶",
                "艮",
                "虍",
                "臼",
                "老",
                "耒",
                "米",
                "糸",
                "齐",
                "肉",
                "色",
                "舌",
                "糹",
                "网",
                "西",
                "覀",
                "行",
                "血",
                "羊",
                "页",
                "衣",
                "羽",
                "聿",
                "至",
                "舟",
                "竹",
                "自"
            )
        ),
        CharacterRadical(
            stroke = "七画",
            listOf(
                "辰",
                "赤",
                "辵",
                "豆",
                "谷",
                "龟",
                "角",
                "里",
                "卤",
                "麦",
                "身",
                "豕",
                "辛",
                "言",
                "邑",
                "酉",
                "鱼",
                "豸",
                "走",
                "足"
            )
        ),
        CharacterRadical(
            stroke = "八画",
            listOf("采", "齿", "非", "阜", "金", "隶", "黾", "青", "鱼", "雨", "隹", "釒")
        ),
        CharacterRadical(
            stroke = "九画",
            listOf("革", "骨", "鬼", "韭", "面", "食", "飠", "首", "香", "音")
        ),
        CharacterRadical(
            stroke = "十画",
            listOf("髟", "高", "鬲")
        ),
        CharacterRadical(
            stroke = "十一画",
            listOf("黄", "鹿", "麻")
        ),
        CharacterRadical(
            stroke = "十二画",
            listOf("鼎", "黑", "黍")
        ),
        CharacterRadical(
            stroke = "十三画",
            listOf("鼓", "鼠", "裏")
        ),
        CharacterRadical(
            stroke = "十四画",
            listOf("鼻")
        ),
        CharacterRadical(
            stroke = "十七画",
            listOf("龠")
        ),
    )

    override fun strokes(): List<CharacterStroke> = listOf(
        CharacterStroke("一画", 1),
        CharacterStroke("二画", 2),
        CharacterStroke("三画", 3),
        CharacterStroke("四画", 4),
        CharacterStroke("五画", 5),
        CharacterStroke("六画", 6),
        CharacterStroke("七画", 7),
        CharacterStroke("八画", 8),
        CharacterStroke("九画", 9),
        CharacterStroke("十画", 10),
        CharacterStroke("十一画", 11),
        CharacterStroke("十二画", 12),
        CharacterStroke("十三画", 13),
        CharacterStroke("十四画", 14),
        CharacterStroke("十五画", 15),
        CharacterStroke("十六画", 16),
        CharacterStroke("十七画", 17),
        CharacterStroke("十八画", 18),
        CharacterStroke("十九画", 19),
        CharacterStroke("二十画", 20),
        CharacterStroke("二十一画", 21),
        CharacterStroke("二十二画", 22),
        CharacterStroke("二十三画", 23),
        CharacterStroke("二十四画", 24),
        CharacterStroke("二十五画", 25),
        CharacterStroke("二十六画", 26),
        CharacterStroke("二十七画", 27),
        CharacterStroke("二十八画", 28),
        CharacterStroke("二十九画", 29),
        CharacterStroke("三十画", 30),
        CharacterStroke("三十一画", 31),
        CharacterStroke("三十二画", 32),
        CharacterStroke("三十三画", 33),
        CharacterStroke("三十四画", 34),
        CharacterStroke("三十五画", 35),
        CharacterStroke("三十六画", 36),
        CharacterStroke("三十九画", 39),
        CharacterStroke("五十一画", 51),
    )

    override fun get(id: Int): Flow<DictionaryEntity> = dao.get(id)

    override fun search(character: String): Flow<List<DictionaryEntity>> = dao.get(character)

    override fun searchByPinyin(pinyin: String): Flow<List<DictionaryEntity>> =
        dao.getByPinyin(pinyin)

    override fun searchByRadical(radical: String): Flow<List<DictionaryEntity>> =
        dao.getByRadical(radical)

    override fun searchByStroke(stroke: Int): Flow<List<DictionaryEntity>> = dao.getByStroke(stroke)
}