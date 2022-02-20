package com.nosorae.thingsflow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Issue(
    val body: String,
    val number: Int,
    val title: String,
    val avatar_url: String,
    val login: String // nickname 처럼 생각하면 된다.
): Parcelable