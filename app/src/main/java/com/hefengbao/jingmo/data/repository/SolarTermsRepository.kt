package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.SolarTerm

interface SolarTermsRepository {
    fun getList(): List<SolarTerm>
}