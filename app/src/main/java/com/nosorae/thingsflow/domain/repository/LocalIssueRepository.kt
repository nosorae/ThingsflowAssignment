package com.nosorae.thingsflow.domain.repository

import com.nosorae.thingsflow.domain.model.Issue
import kotlinx.coroutines.flow.Flow

interface LocalIssueRepository {
    fun loadIssues(): Flow<List<Issue>>
    suspend fun insertIssues(issues: List<Issue>)
}