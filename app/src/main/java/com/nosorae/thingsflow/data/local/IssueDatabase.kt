package com.nosorae.thingsflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nosorae.thingsflow.domain.model.Issue

@Database(
    entities = [Issue::class],
    version =  1
)
abstract class IssueDatabase: RoomDatabase() {
    abstract val issueDao: IssueDao
}