package com.hefengbao.jingmo.data.enums


import com.hefengbao.jingmo.App
import com.hefengbao.jingmo.R

enum class Category(val model: String, val label: String) {
    ChinaWorldCulturalHeritage(
        "china_worldcultureheritage",
        App.appContext.resources.getString(R.string.china_worldcultureheritage)
    ),
    ChineseAntitheticalCouplet(
        "chinese_antitheticalcouplet",
        App.appContext.resources.getString(R.string.chinese_antitheticalcouplet)
    ),
    ChineseCharacter(
        "chinese_character",
        App.appContext.resources.getString(R.string.chinese_character)
    ),
    ChineseExpression(
        "chinese_expression",
        App.appContext.resources.getString(R.string.chinese_expression)
    ),
    ChineseIdiom(
        "chinese_idiom",
        App.appContext.resources.getString(R.string.chinese_idiom)
    ),
    ChineseKnowledge(
        "chinese_knowledge",
        App.appContext.resources.getString(R.string.chinese_knowledge)
    ),
    ChineseLyric(
        "chinese_lyric",
        App.appContext.resources.getString(R.string.chinese_lyric)
    ),
    ChineseModernPoetry(
        "chinese_modernpoetry",
        App.appContext.resources.getString(R.string.chinese_modernpoetry)
    ),
    ChineseProverb(
        "chinese_proverb",
        App.appContext.resources.getString(R.string.chinese_proverb)
    ),
    ChineseQuote(
        "chinese_quote",
        App.appContext.resources.getString(R.string.chinese_quote)
    ),
    ChineseRiddle(
        "chinese_riddle",
        App.appContext.resources.getString(R.string.chinese_riddle)
    ),
    ChineseTongueTwister(
        "chinese_tonguetwister",
        App.appContext.resources.getString(R.string.chinese_tonguetwister)
    ),
    ChineseWisecrack(
        "chinese_wisecrack",
        App.appContext.resources.getString(R.string.chinese_wisecrack)
    ),
    ClassicalLiteratureClassicPoem(
        "classicalliterature_classicpoem",
        App.appContext.resources.getString(R.string.classicalliterature_classicpoem)
    ),
    ClassicalLiteraturePeople(
        "classicalliterature_people",
        App.appContext.resources.getString(R.string.classicalliterature_people)
    ),
    ClassicalLiteratureSentence(
        "classicalliterature_sentence",
        App.appContext.resources.getString(R.string.classicalliterature_sentence)
    ),
    ClassicalLiteratureWriting(
        "classicalliterature_writing",
        App.appContext.resources.getString(R.string.classicalliterature_writing)
    ),
    TraditionalCultureCalendar(
        "traditionalculture_calendar",
        App.appContext.resources.getString(R.string.traditionalculture_calendar)
    ),
    TraditionalCultureColor(
        "traditionalculture_color",
        App.appContext.resources.getString(R.string.traditionalculture_color)
    ),
    TraditionalCultureFestival(
        "traditionalculture_festival",
        App.appContext.resources.getString(R.string.traditionalculture_festival)
    ),
    TraditionalCultureSolarTerm(
        "traditionalculture_solarterm",
        App.appContext.resources.getString(R.string.traditionalculture_solarterm)
    );

    companion object {
        infix fun from(model: String): Category? =
            Category.entries.firstOrNull { it.model == model }
    }
}