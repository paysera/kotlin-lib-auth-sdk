package com.paysera.lib.auth.entities

class SystemToken(
    val value: String,
    val expiresIn: Int,
    val scope: String,
    val audience: String
)