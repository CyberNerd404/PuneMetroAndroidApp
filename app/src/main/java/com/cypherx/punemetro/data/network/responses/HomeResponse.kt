package com.cypherx.punemetro.data.network.responses

import com.cypherx.punemetro.db.entities.Station

data class HomeResponse(
    val isSuccessful : Boolean?,
    val message : String,
    val station : Station?
)