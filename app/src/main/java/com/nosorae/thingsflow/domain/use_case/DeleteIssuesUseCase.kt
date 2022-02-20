package com.nosorae.thingsflow.domain.use_case

import com.nosorae.thingsflow.domain.repository.LocalIssueRepository

class DeleteIssuesUseCase(
    private val repository: LocalIssueRepository
) {
    suspend operator fun invoke() {
        repository.deleteIssues()
    }
}