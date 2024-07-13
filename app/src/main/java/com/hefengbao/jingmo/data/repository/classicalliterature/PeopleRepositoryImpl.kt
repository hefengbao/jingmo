/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.classicalliterature

import com.hefengbao.jingmo.data.database.dao.ClassicalLiteraturePeopleDao
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val classicalLiteraturePeopleDao: ClassicalLiteraturePeopleDao
) : PeopleRepository {
    override fun random(): Flow<PeopleEntity> = classicalLiteraturePeopleDao.random()

    override fun get(id: Int): Flow<PeopleEntity> =
        classicalLiteraturePeopleDao.getPeopleById(id)

    override fun getByName(name: String): Flow<PeopleEntity> =
        classicalLiteraturePeopleDao.getPeopleByName(name)

    override fun search(query: String) = classicalLiteraturePeopleDao.search("%$query%")

    override fun recommendList(): List<String> {
        return listOf(
            "诗经",
            "屈原",
            "曹操",
            "曹丕",
            "曹植",
            "陆机",
            "陆云",
            "谢灵运",
            "陶潜",
            "卢照邻",
            "骆宾王",
            "王勃",
            "杨炯",
            "贺知章",
            "陈子昂",
            "张若虚",
            "张九龄",
            "王之涣",
            "孟浩然",
            "王昌龄",
            "高适",
            "李白",
            "王维",
            "崔颢",
            "杜甫",
            "岑参",
            "张继",
            "韦应物",
            "孟郊",
            "韩愈",
            "刘禹锡",
            "白居易",
            "柳宗元",
            "李贺",
            "李商隐",
            "温庭筠",
            "杜牧",
            "罗隐",
            "司空图",
            "韦庄",
            "李煜",
            "林逋",
            "范仲淹",
            "欧阳修",
            "晏殊",
            "曾巩",
            "王安石",
            "晏几道",
            "苏轼",
            "黄庭坚",
            "秦观",
            "周邦彦",
            "李清照",
            "陆游",
            "范成大",
            "杨万里",
            "朱熹",
            "辛弃疾",
            "姜夔",
            "文天祥",
            "元好问",
            "关汉卿",
            "马致远",
            "纳兰性德",
            "仓央嘉措",
            "王国维",
            "李叔同",
            "毛泽东",
            "柳亚子"
        )
    }
}