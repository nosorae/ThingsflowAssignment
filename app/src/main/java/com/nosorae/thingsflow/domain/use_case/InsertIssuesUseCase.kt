package com.nosorae.thingsflow.domain.use_case

import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.LocalIssueRepository
import kotlinx.coroutines.flow.flow

class InsertIssuesUseCase(
    private val repository: LocalIssueRepository
) {
    operator fun invoke(issues: List<Issue>) = flow<Resource<Unit>> {
        try {
            emit(Resource.Loading())
            repository.insertIssues(issues)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(message = "예상치 못한 에러 발생"))
        }
    }
}