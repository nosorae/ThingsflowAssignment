package com.nosorae.thingsflow.data.repository

import com.nosorae.thingsflow.data.local.IssueDao
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.LocalIssueRepository
import kotlinx.coroutines.flow.Flow

class LocalIssueRepositoryImpl(
    private val dao: IssueDao
): LocalIssueRepository {
    override fun getIssues(): Flow<List<Issue>> {
        return dao.getIssues()
    }

    override suspend fun insertIssues(issues: List<Issue>) {
        dao.insertIssues(issues)
    }
}