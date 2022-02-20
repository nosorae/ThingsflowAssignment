package com.nosorae.thingsflow.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Issue(
    @PrimaryKey val id: Int,
    val body: String? = "",
    val number: Int? = -1,
    val title: String? = "",
    val avatar_url: String? = "",
    val login: String? = "" // nickname 처럼 생각하면 된다.
): Parcelable