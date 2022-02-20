package com.nosorae.thingsflow.presentation.issue_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nosorae.thingsflow.common.Constants.LOG_TAG
import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.common.SingleLiveData
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.use_case.GetIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase
): ViewModel() {

    private val _issues = MutableLiveData<List<Issue>>(emptyList())
    val issues: LiveData<List<Issue>> get() = _issues

    private val _errorMessage = SingleLiveData<String>()
    val errorMessage: SingleLiveData<String> get() = _errorMessage

    val lastOrg = ""
    val lastRepo = ""


    init {
        getIssues("asdf", "asdf")
    }

    fun getIssues(org: String, repo: String) {
        getIssuesUseCase(org, repo)
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { list ->
                            _issues.value = list
                        }
                    }
                    is Resource.Loading -> {
                        // TODO 추가 과제까지 완료 시 로딩 뷰도 추가 예정
                    }
                    is Resource.Error -> {
                        Log.e(LOG_TAG, "ViewModel : \n ${result.message}")
                        _errorMessage.value = result.message
                    }
                }
            }.launchIn(viewModelScope)
    }

}