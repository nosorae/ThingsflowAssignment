package com.nosorae.thingsflow.domain.use_case

import android.util.Log
import com.nosorae.thingsflow.common.Constants.LOG_TAG
import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.common.parseErrorBody
import com.nosorae.thingsflow.data.remote.dto.toIssue
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.repository.RemoteIssueRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: RemoteIssueRepository,
) {
    operator fun invoke(org: String, repo: String) = flow<Resource<List<Issue>>> {
        try {
            emit(Resource.Loading())

            val issues = repository.getIssues(
                org = org,
                repo = repo
            )
            emit(Resource.Success(issues.map { dto ->
                dto.toIssue()
            }))
        } catch (e: HttpException) {
            val errorMessage = e.parseErrorBody()
            emit(Resource.Error(message = errorMessage))
        } catch (e: IOException) {
            emit(Resource.Error(message = "서버와 연결이 되지 않습니다. 인터넷 연결을 확인해 주세요"))
        }
    }
}