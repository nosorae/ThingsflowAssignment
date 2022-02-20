package com.nosorae.thingsflow.domain.use_case

import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.LocalIssueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LoadIssuesUseCase(
    private val repository: LocalIssueRepository
) {
    operator fun invoke(): Flow<List<Issue>> {
        return repository.loadIssues()
    }
}