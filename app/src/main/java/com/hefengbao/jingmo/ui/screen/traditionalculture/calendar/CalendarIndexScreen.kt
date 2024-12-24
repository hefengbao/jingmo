/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.calendar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.ui.component.BackgroundTitle
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.nlf.calendar.Lunar
import com.nlf.calendar.Solar
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import java.util.Date

@Composable
fun CalendarIndexRoute(
    onBackClick: () -> Unit
) {
    CalendarIndexScreen(
        onBackClick = onBackClick,
    )
}

@Composable
fun CalendarIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var showCalendar by rememberSaveable { mutableStateOf(false) }
    var showShengxiaoDescr by rememberSaveable { mutableStateOf(false) }
    var showJieqiDescr by rememberSaveable { mutableStateOf(false) }
    var showSanfuDescr by rememberSaveable { mutableStateOf(false) }
    var showShujiuDescr by rememberSaveable { mutableStateOf(false) }
    var showLuDescr by rememberSaveable { mutableStateOf(false) }
    var showYuexiangDescr by rememberSaveable { mutableStateOf(false) }
    var showWuhouDescr by rememberSaveable { mutableStateOf(false) }
    var showSigongDescr by rememberSaveable { mutableStateOf(false) }
    var showLiuyaoDescr by rememberSaveable { mutableStateOf(false) }
    var showQizhengDescr by rememberSaveable { mutableStateOf(false) }
    var showXiuDescr by rememberSaveable { mutableStateOf(false) }
    var showPengzuDescr by rememberSaveable { mutableStateOf(false) }
    var showJishenDescr by rememberSaveable { mutableStateOf(false) }
    var showTaisuiDescr by rememberSaveable { mutableStateOf(false) }
    var showNayinDescr by rememberSaveable { mutableStateOf(false) }
    var mills by rememberSaveable { mutableLongStateOf(Clock.System.now().toEpochMilliseconds()) }

    val localDate =
        Instant.fromEpochMilliseconds(mills).toLocalDateTime(TimeZone.currentSystemDefault()).date

    Log.i(
        "CalendarIndexScreen",
        "year = ${localDate.year}, month = ${localDate.monthNumber}, day = ${localDate.dayOfMonth}"
    )

    val lunar = Lunar.fromDate(Date(mills))

    val solar = Solar.fromDate(Date(mills))

    SimpleScaffold(
        title = "老黄历",
        onBackClick = onBackClick,
        actions = {
            IconButton(
                onClick = { showCalendar = true }
            ) {
                Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = "选择日期")
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BackgroundTitle("阳历")
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "$localDate ${solar.xingZuo}座 星期${solar.weekInChinese}")
                    if (solar.isLeapYear) {
                        Text(
                            text = "闰年",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            if (solar.festivals.isNotEmpty() && solar.otherFestivals.isNotEmpty()) {
                item {
                    Item(
                        title = "节日",
                        titleDescr = "${solar.festivals.joinToString(" ")} ${
                            solar.otherFestivals.joinToString(
                                " "
                            )
                        }",
                        onShowDescrClick = {}
                    )
                }
            }

            item {
                BackgroundTitle("阴历")
            }

            item {
                Text(text = "${lunar.yearInChinese}年${lunar.monthInChinese}月${lunar.dayInChinese}日")
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("${lunar.yearInGanZhi}年 ${lunar.monthInGanZhi}月 ${lunar.dayInGanZhi}日")

                    if (localDate == Clock.System.todayIn(TimeZone.currentSystemDefault())) {
                        Text(text = "${lunar.timeZhi}时", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            item {
                Item(
                    title = "生肖",
                    titleDescr = lunar.yearShengXiao,
                    showDescr = showShengxiaoDescr,
                    onShowDescrClick = { showShengxiaoDescr = !showShengxiaoDescr },
                    descr = """
                        十二生肖，又叫属相，是中国与十二地支相配以人出生年份的十二种动物，包括鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪。

                        十二生肖是十二地支的形象化代表，即子（鼠）、丑（牛）、寅（虎）、卯（兔）、辰（龙）、巳（蛇）、午（马）、未（羊）、申（猴）、酉（鸡）、戌（狗）、亥（猪）。
                    """.trimIndent()
                )
            }

            if (lunar.festivals.isNotEmpty() && lunar.otherFestivals.isNotEmpty()) {
                item {
                    Item(
                        title = "节日",
                        titleDescr = "${lunar.festivals.joinToString(" ")} ${
                            lunar.otherFestivals.joinToString(
                                " "
                            )
                        }",
                        onShowDescrClick = {}
                    )
                }
            }

            lunar.currentJieQi?.let {
                item {
                    Item(
                        title = "节气",
                        titleDescr = it.name,
                        showDescr = showJieqiDescr,
                        onShowDescrClick = { showJieqiDescr = !showJieqiDescr },
                        descr = """
                            二十四节气包括十二节（立春、惊蛰、清明、立夏、芒种、小暑、立秋、白露、寒露、立冬、大雪、小寒）和十二气（雨水、春分、谷雨、小满、夏至、大暑、处暑、秋分、霜降、小雪、冬至、大寒）。
                        """.trimIndent()
                    )
                }
            }

            lunar.nextJieQi?.let {
                item {
                    Item(
                        title = "下一节气",
                        titleDescr = "${it.name} ${it.solar.toYmdHms()}",
                        showDescr = showJieqiDescr,
                        onShowDescrClick = { showJieqiDescr = !showJieqiDescr },
                        descr = """
                            二十四节气包括十二节（立春、惊蛰、清明、立夏、芒种、小暑、立秋、白露、寒露、立冬、大雪、小寒）和十二气（雨水、春分、谷雨、小满、夏至、大暑、处暑、秋分、霜降、小雪、冬至、大寒）。
                        """.trimIndent()
                    )
                }
            }

            lunar.shuJiu?.let {
                item {
                    Item(
                        title = "数九",
                        titleDescr = it.toFullString(),
                        showDescr = showShujiuDescr,
                        onShowDescrClick = { showShujiuDescr = !showShujiuDescr },
                        descr = """
                            数九，又称冬九九，是中国民间一种计算寒天与春暖花开日期的方法。一般“三九、四九”是一年中最冷的时段。当数到九个“九天”（九九八十一天），便春深日暖、万物生机盎然，是春耕的时候了。

                            民谚云：“夏至三庚入伏，冬至逢壬数九。”

                            数九即是从冬至逢壬日算起，每九天算一“九”。但是大部分人都不知道壬日是哪一天，就干脆采用按冬至日作为一九的开始了。这里的算法也是按冬至日起算。

                            还记得小时候学的数九歌吗？

                            一九二九不出手，三九四九冰上走，五九六九沿河看柳，七九河开，八九燕来，九九加一九，耕牛遍地走。
                        """.trimIndent()
                    )
                }
            }

            lunar.fu?.let {
                item {
                    Item(
                        title = "三伏",
                        titleDescr = it.toFullString(),
                        showDescr = showSanfuDescr,
                        onShowDescrClick = { showSanfuDescr = !showSanfuDescr },
                        descr = """
                            三伏，是初伏、中伏和末伏的统称，是一年中最热的时节。

                            民谚云：“夏至三庚入伏，冬至逢壬数九。”

                            三伏即是从夏至后第3个庚日算起，初伏为10天，中伏为10天或20天，末伏为10天。当夏至与立秋之间出现4个庚日时中伏为10天，出现5个庚日则为20天。
                        """.trimIndent()
                    )
                }
            }

            item {
                Item(
                    title = "月名",
                    titleDescr = lunar.season,
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "月相",
                    titleDescr = "${lunar.yueXiang}月",
                    showDescr = showYuexiangDescr,
                    onShowDescrClick = { showYuexiangDescr = !showYuexiangDescr },
                    descr = """
                        月相，是天文学中对于地球上看到的月球被太阳照明部分的称呼。随着月亮每天在星空中自东向西移动一大段距离，它的形状也在不断地变化着，这就是月亮位相变化，叫做月相。

                        月相与日期的关系如下表所示：

                        农历日期	月相名
                        初一      朔月（也称新月）
                        初二      既朔月
                        初三      蛾眉新月
                        初四      蛾眉新月
                        初五      蛾眉月
                        初六      夕月
                        初七      上弦月
                        初八      上弦月
                        初九      九夜月
                        初十      宵月
                        十一      宵月
                        十二      宵月
                        十三      渐盈凸月
                        十四      小望月
                        十五      望月（也称满月）
                        十六      既望月
                        十七      立待月
                        十八      居待月
                        十九      寝待月
                        二十      更待月
                        二十一   渐亏凸月
                        二十二   下弦月
                        二十三   下弦月
                        二十四   有明月
                        二十五   有明月
                        二十六   蛾眉残月
                        二十七   蛾眉残月
                        二十八   残月
                        二十九   晓月
                        三十      晦月
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "物候",
                    titleDescr = "${lunar.wuHou} ${lunar.hou}",
                    showDescr = showWuhouDescr,
                    onShowDescrClick = { showWuhouDescr = !showWuhouDescr },
                    descr = """
                        七十二候，是我国古代用来指导农事活动的历法补充。它是根据黄河流域的地理、气候、和自然界的一些景象编写而成，以五日为候，三候为气，六气为时，四时为岁，一年“二十四节气”共七十二候。各候均以一个物候现象相应，称“候应”。其中植物候应有植物的幼芽萌动、开花、结实等；动物候应有动物的始振、始鸣、交配、迁徙等；非生物候应有始冻、解冻、雷始发声等。七十二候“候应”的依次变化，反映了一年中物候和气候变化的一般情况。

                        每个节气持续15天左右，每隔5天为一候，因此从节气日开始，分别为初候、二候、三候。
                    """.trimIndent()
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "宜", color = Color.Green)
                    Text(text = lunar.dayYi.joinToString(" "))
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "忌", color = Color.Red)
                    Text(text = lunar.dayJi.joinToString(" "))
                }
            }

            item {
                Item(
                    title = "四宫（神兽）",
                    titleDescr = "${lunar.gong}方${lunar.shou}",
                    showDescr = showSigongDescr,
                    onShowDescrClick = { showSigongDescr = !showSigongDescr },
                    descr = """
                        四宫代表东南西北四个方位。
                        
                        四神兽对应四宫，分别是东青龙、南朱雀、西白虎、北玄武。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "六曜",
                    titleDescr = lunar.liuYao,
                    showDescr = showLiuyaoDescr,
                    onShowDescrClick = { showLiuyaoDescr = !showLiuyaoDescr },
                    descr = """
                        六曜，又称孔明六曜星或小六壬，是中国传统历法中的一种附注。它包括先胜、友引、 先负、佛灭、大安和赤口六个词汇，分别表示当天宜行何事，用以作为判定每日凶吉的参考。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "七曜",
                    titleDescr = lunar.zheng,
                    showDescr = showQizhengDescr,
                    onShowDescrClick = { showQizhengDescr = !showQizhengDescr },
                    descr = """
                        七曜，又称七政、七纬、七耀等。

                        “七曜日”分别代表一周七天的叫法最早出现于两河流域的古巴比伦文明。公元前七百年左右，古巴比伦出现了一个星期分为七天的制度，四星期合为一个月。

                        在日本、韩国和朝鲜，一星期中的各天并不是按数字顺序，而是有着特定的名字，是以“七曜”来分别命名的。土曜日是星期六，日曜日是星期天，月曜日是星期一，火曜日是星期二，水曜日是星期三，木曜日是星期四，金曜日是星期五。

                        在中国，起初也是以七曜命名一星期中的各天，将日（太阳）、月（太阴）、金（太白）太白金星是不是很耳熟？、木（岁星）、水（辰星）、火（荧惑）、土（填星、镇星）等称为七曜，到清末才逐渐改为现在“星期”的叫法。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "二十八宿",
                    titleDescr = "${lunar.gong}方${lunar.xiu}${lunar.zheng}${lunar.animal}（${lunar.xiuLuck}）",
                    showDescr = showXiuDescr,
                    onShowDescrClick = { showXiuDescr = !showXiuDescr },
                    descr = """
                        二十八宿，是黄道附近的二十八组星象总称。上古时代人们根据日月星辰的运行轨迹和位置，把黄道附近的星象划分为二十八组，俗称二十八宿。

                        二十八宿包括：

                        东方七宿：角、亢、氐、房、心、尾、箕；

                        北方七宿：斗、牛、女、虚、危、室、壁；

                        西方七宿：奎、娄、胃、昴、毕、觜、参；

                        南方七宿：井、鬼、柳、星、张、翼、轸。
                        
                        二十八星宿与七政（日、月、金、木、水、火、土）分别对应：
                        
                        角 => 木，
                        井 => 木，
                        奎 => 木，
                        斗 => 木，
                        亢 => 金，
                        鬼 => 金，
                        娄 => 金，
                        牛 => 金，
                        氐 => 土，
                        柳 => 土，
                        胃 => 土，
                        女 => 土，
                        房 => 日，
                        星 => 日，
                        昴 => 日，
                        虚 => 日，
                        心 => 月，
                        张 => 月，
                        毕 => 月，
                        危 => 月，
                        尾 => 火，
                        翼 => 火，
                        觜 => 火，
                        室 => 火，
                        箕 => 水，
                        轸 => 水，
                        参 => 水，
                        壁 => 水。
                        
                        二十八星宿与28种动物分别对应：
                        
                        角 => 蛟，
                        斗 => 獬，
                        奎 => 狼，
                        井 => 犴，
                        亢 => 龙，
                        牛 => 牛，
                        娄 => 狗，
                        鬼 => 羊，
                        女 => 蝠，
                        氐 => 貉，
                        胃 => 彘，
                        柳 => 獐，
                        房 => 兔，
                        虚 => 鼠，
                        昴 => 鸡，
                        星 => 马，
                        心 => 狐，
                        危 => 燕，
                        毕 => 乌，
                        张 => 鹿，
                        尾 => 虎，
                        室 => 猪，
                        觜 => 猴，
                        翼 => 蛇，
                        箕 => 豹，
                        壁 => 獝，
                        参 => 猿，
                        轸 => 蚓。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "日禄",
                    titleDescr = lunar.dayLu,
                    showDescr = showLuDescr,
                    onShowDescrClick = { showLuDescr = !showLuDescr },
                    descr = """
                        禄神为四柱神煞之一，是民间信仰中的主司官禄之神。
        
                        甲禄在寅，乙禄在卯，丙戊禄在巳，丁己禄在午，庚禄在申，辛禄在酉，壬禄在亥，癸禄在子。
        
                        禄在年支叫岁禄，禄在月支叫建禄，禄在日支叫专禄（也叫日禄），禄在时支叫归禄。    
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "彭祖百忌",
                    titleDescr = "${lunar.pengZuGan}\n${lunar.pengZuZhi}",
                    showDescr = showPengzuDescr,
                    onShowDescrClick = { showPengzuDescr = !showPengzuDescr },
                    descr = """
                        彭祖百忌指的是在天干地支记日中的某日或当日里的某时不要做某事否则会发生某事。彭祖，道家先驱，是中国远古道家的重要人物，他以善养生而长寿。

                        彭祖创作了一首打油诗，对应10天干和12地支，说明要忌讳的事情：
                        
                        天干：
                        
                        甲不开仓财物耗散，
                        乙不栽植千株不长，
                        丙不修灶必见灾殃，
                        丁不剃头头必生疮，
                        戊不受田田主不祥，
                        己不破券二比并亡，
                        庚不经络织机虚张，
                        辛不合酱主人不尝，
                        壬不泱水更难提防，
                        癸不词讼理弱敌强。
                        
                        地支：
                        
                        子不问卜自惹祸殃，
                        丑不冠带主不还乡，
                        寅不祭祀神鬼不尝，
                        卯不穿井水泉不香，
                        辰不哭泣必主重丧，
                        巳不远行财物伏藏，
                        午不苫盖屋主更张，
                        未不服药毒气入肠，
                        申不安床鬼祟入房，
                        酉不会客醉坐颠狂，
                        戌不吃犬作怪上床，
                        亥不嫁娶不利新郎。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "吉神方位",
                    titleDescr = "",
                    showDescr = showJishenDescr,
                    onShowDescrClick = { showJishenDescr = !showJishenDescr },
                    descr = """
                        吉神包括：喜神、阳贵神、阴贵神、福神、财神。
                        
                        《喜神方位歌》：
                        
                        甲己在艮乙庚乾，
                        丙辛坤位喜神安。
                        丁壬只在离宫坐，
                        戊癸原在在巽间。
                        
                        《阳贵神歌》：
                        
                        甲戊坤艮位，
                        乙己是坤坎，
                        庚辛居离艮，
                        丙丁兑与乾，
                        震巽属何日，
                        壬癸贵神安。
                        
                        《阴贵神歌》：
                        
                        甲戊见牛羊，
                        乙己鼠猴乡，
                        丙丁猪鸡位，
                        壬癸蛇兔藏，
                        庚辛逢虎马，
                        此是贵神方。
                        
                        《福神方位歌》有两个流派，这里默认采用了流派2，其中流派1为：
                        
                        甲乙东南是福神，
                        丙丁正东是堪宜，
                        戊北己南庚辛坤，
                        壬在乾方癸在西。
                        
                        流派2为：
                        
                        甲己正北是福神，
                        丙辛西北乾宫存，
                        乙庚坤位戊癸艮，
                        丁壬巽上好追寻。
                        
                        《财神方位歌》：
                        
                        甲乙东北是财神，
                        丙丁向在西南寻，
                        戊己正北坐方位，
                        庚辛正东去安身，
                        壬癸原来正南坐，
                        便是财神方位真。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "喜神",
                    titleDescr = "（${lunar.dayPositionXi}）${lunar.dayPositionXiDesc}",
                    onShowDescrClick = {}
                )
            }
            item {
                Item(
                    title = "福神",
                    titleDescr = "（${lunar.dayPositionFu}）${lunar.dayPositionFuDesc}",
                    onShowDescrClick = {}
                )
            }
            item {
                Item(
                    title = "财神",
                    titleDescr = "（${lunar.dayPositionCai}）${lunar.dayPositionCaiDesc}",
                    onShowDescrClick = {}
                )
            }
            item {
                Item(
                    title = "阳贵神",
                    titleDescr = "（${lunar.dayPositionYangGui}）${lunar.dayPositionYangGuiDesc}",
                    onShowDescrClick = {}
                )
            }
            item {
                Item(
                    title = "阴贵神",
                    titleDescr = "（${lunar.dayPositionYinGui}）${lunar.dayPositionYinGuiDesc}",
                    onShowDescrClick = {}
                )
            }
            item {
                Item(
                    title = "太岁方位",
                    titleDescr = "",
                    showDescr = showTaisuiDescr,
                    onShowDescrClick = { showTaisuiDescr = !showTaisuiDescr },
                    descr = """
                        四库全书收录的《御定月令辑要》曰：“太岁者，主宰一岁之尊神。凡吉事勿冲之，凶事勿犯之，凡修造方向等事尤宜慎避。太岁所在之方不宜兴工动土，否则必有灾祸。”

                        经常听说的太岁头上动土，就出自这里。

                        本命年，也就是值太岁。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "年太岁",
                    titleDescr = "（${lunar.getYearPositionTaiSui(1)}）${
                        lunar.getYearPositionTaiSuiDesc(
                            1
                        )
                    }",
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "月太岁",
                    titleDescr = "（${lunar.getMonthPositionTaiSui(1)}）${
                        lunar.getMonthPositionTaiSuiDesc(
                            1
                        )
                    }",
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "日太岁",
                    titleDescr = "（${lunar.getDayPositionTaiSui(1)}）${
                        lunar.getDayPositionTaiSuiDesc(
                            1
                        )
                    }",
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "纳音",
                    titleDescr = "",
                    showDescr = showNayinDescr,
                    onShowDescrClick = { showNayinDescr = !showNayinDescr },
                    descr = """
                        天干有天干的五行，地支有地支的五行，天干与地支配合后会变成新的五行，称为“纳音五行”。

                        六十甲子纳音表五行歌：

                        甲子、乙丑——海中金，丙寅、丁卯——炉中火。
                        戊辰、己巳——大林木，庚午、辛未——路旁土。
                        壬申、癸酉——剑锋金，甲戌、乙亥——山头火。　
                        丙子、丁丑——涧[jiàn]下水，戊寅、己卯——城头土。
                        庚辰、辛巳——白蜡金，壬午、癸未——杨柳木。
                        甲申、乙酉——泉中水，丙戌、丁亥——屋上土。
                        戊子、己丑——霹雳火，庚寅、辛卯——松柏木。
                        壬辰、癸巳——长流水，甲午、乙未——砂石金。　
                        丙申、丁酉——山下火，戊戌、己亥——平地木。　
                        庚子、辛丑——壁上土，壬寅、癸卯——金箔[bó]金。
                        甲辰、乙巳——灯头火，丙午、丁未——天河水。
                        戊申、己酉——大驿[yì]土，庚戌、辛亥——钗钏[chāi chuàn]金。
                        壬子、癸丑——桑柘[zhè]木，甲寅、乙卯——大溪水。
                        丙辰、丁巳——沙中土，戊午、己未——天上火。　
                        庚申、辛酉——石榴木，壬戌、癸亥——大海水。
                        
                        由于年、月、日都可由天干地支表示，所以年月日都对应着纳音五行。
                    """.trimIndent()
                )
            }

            item {
                Item(
                    title = "${lunar.yearInGanZhi}年",
                    titleDescr = lunar.yearNaYin,
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "${lunar.monthInGanZhi}月",
                    titleDescr = lunar.monthNaYin,
                    onShowDescrClick = {}
                )
            }

            item {
                Item(
                    title = "${lunar.dayInGanZhi}日",
                    titleDescr = lunar.dayNaYin,
                    onShowDescrClick = {}
                )
            }
        }
    }

    if (showCalendar) {
        DatePickerModal(
            onDateSelected = { it?.let { mills = it } },
            onDismiss = { showCalendar = false },
            mills = mills
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    mills: Long
) {
    val datePickerState = rememberDatePickerState(mills)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("选择")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
private fun Item(
    title: String,
    titleDescr: String,
    onShowDescrClick: () -> Unit,
    showDescr: Boolean = false,
    descr: String? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(
                    text = titleDescr,
                )
            }
            if (descr != null) {
                IconButton(onClick = onShowDescrClick) {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = "点击查看说明")
                }
            }
        }
        if (descr != null && showDescr) {
            Card {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = descr,
                )
            }
        }
    }
}