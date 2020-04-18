package com.cypherx.punemetro.db.entities

import androidx.room.Entity


@Entity
data class Station(
    var station_name : String? = null,
    var line : Int? = null
)