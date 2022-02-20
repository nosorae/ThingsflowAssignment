package com.nosorae.thingsflow.domain.use_case

import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.data.remote.dto.toIssue
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.RemoteIssueRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: RemoteIssueRepository
) {
    operator fun invoke(org: String, repo: String) = flow<Resource<List<Issue>>> {
        try {
            emit(Resource.Loading())
            val issues = repository
                .getIssues(
                    org = org,
                    repo = repo
                )
                .map { dto ->
                    dto.toIssue()
                }
            emit(Resource.Success(issues))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "예상치 못한 에러 발생"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "서버와 연결이 되지 않습니다. 인터넷 연결을 확인해 주세요"))
        }
    }
}