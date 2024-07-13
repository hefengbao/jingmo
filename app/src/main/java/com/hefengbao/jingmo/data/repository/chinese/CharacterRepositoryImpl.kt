/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import com.hefengbao.jingmo.data.database.dao.ChineseDictionaryDao
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.model.chinese.character.Pinyin
import com.hefengbao.jingmo.data.model.chinese.character.Radical
import com.hefengbao.jingmo.data.model.chinese.character.Stroke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: ChineseDictionaryDao
) : CharacterRepository {

    override fun pinyins(): List<Pinyin> = listOf(
        Pinyin(
            alphabet = "A",
            pinyin = listOf("a", "ai", "an", "ang", "ao")
        ),
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
            alphabet = "E",
            pinyin = listOf("e", "ei", "en", "eng", "er")
        ),
        Pinyin(
            alphabet = "F",
            pinyin = listOf("fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu")
        ),
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
            alphabet = "O",
            pinyin = listOf("o", "ou")
        ),
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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
        Pinyin(
            alphabet = "W",
            pinyin = listOf("wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu")
        ),
        Pinyin(
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
        Pinyin(
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
        Pinyin(
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

    override fun radicals(): List<Radical> = listOf(
        Radical(
            stroke = "一画",
            radicals = listOf("丨", "亅", "丿", "乛", "一", "乙", "乚", "丶")
        ),
        Radical(
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
        Radical(
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
        Radical(
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
        Radical(
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
        Radical(
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
        Radical(
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
        Radical(
            stroke = "八画",
            listOf("采", "齿", "非", "阜", "金", "隶", "黾", "青", "鱼", "雨", "隹", "釒")
        ),
        Radical(
            stroke = "九画",
            listOf("革", "骨", "鬼", "韭", "面", "食", "飠", "首", "香", "音")
        ),
        Radical(
            stroke = "十画",
            listOf("髟", "高", "鬲")
        ),
        Radical(
            stroke = "十一画",
            listOf("黄", "鹿", "麻")
        ),
        Radical(
            stroke = "十二画",
            listOf("鼎", "黑", "黍")
        ),
        Radical(
            stroke = "十三画",
            listOf("鼓", "鼠", "裏")
        ),
        Radical(
            stroke = "十四画",
            listOf("鼻")
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

    override fun get(id: Int): Flow<DictionaryEntity> = dao.get(id)

    override fun search(character: String): Flow<List<DictionaryEntity>> = dao.get(character)

    override fun searchByPinyin(pinyin: String): Flow<List<DictionaryEntity>> =
        dao.getByPinyin(pinyin)

    override fun searchByRadical(radical: String): Flow<List<DictionaryEntity>> =
        dao.getByRadical(radical)

    override fun searchByStroke(stroke: Int): Flow<List<DictionaryEntity>> = dao.getByStroke(stroke)
}