package com.nosorae.thingsflow.domain.repository

import com.nosorae.thingsflow.data.remote.dto.IssueDto

interface RemoteIssueRepository {
    suspend fun getIssues(org: String, repo: String): List<IssueDto>
}