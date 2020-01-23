package com.paysera.lib.auth.entities.requests

data class CreateSystemTokenOptionalRequest(
    val audience: String,
    val scope: String
)