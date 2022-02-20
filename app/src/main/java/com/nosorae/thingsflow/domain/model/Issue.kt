package com.nosorae.thingsflow.domain.model

data class Issue(
    val body: String,
    val number: Int,
    val title: String,
    val avatar_url: String,
    val login: String
)