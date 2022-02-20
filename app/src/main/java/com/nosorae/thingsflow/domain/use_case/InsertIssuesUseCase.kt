package com.nosorae.thingsflow.domain.use_case

import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.LocalIssueRepository

class InsertIssuesUseCase(
    private val repository: LocalIssueRepository,
) {
    suspend operator fun invoke(issues: List<Issue>) {
        repository.insertIssues(issues)
    }
}