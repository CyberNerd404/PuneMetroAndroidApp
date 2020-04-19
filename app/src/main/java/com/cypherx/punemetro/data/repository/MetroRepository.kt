package com.cypherx.punemetro.data.repository

import com.cypherx.punemetro.data.network.MyApi
import com.cypherx.punemetro.db.AppDatabase

class MetroRepository(
    private val api: MyApi,
    private val db: AppDatabase
)