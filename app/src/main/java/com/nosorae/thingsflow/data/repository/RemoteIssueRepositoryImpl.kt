package com.nosorae.thingsflow.data.repository

import com.nosorae.thingsflow.data.remote.IssueApi
import com.nosorae.thingsflow.data.remote.dto.IssueDto
import com.nosorae.thingsflow.domain.repository.RemoteIssueRepository

class RemoteIssueRepositoryImpl (
    private val api: IssueApi
): RemoteIssueRepository {
    override suspend fun getIssues(org: String, repo: String): List<IssueDto> {
        return api.getIssues(
            org = org,
            repo = repo
        )
    }
}