package com.nosorae.thingsflow.presentation.issue_list

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nosorae.thingsflow.common.Constants.LOG_TAG
import com.nosorae.thingsflow.common.Constants.PREF_ORG
import com.nosorae.thingsflow.common.Constants.PREF_REPO
import com.nosorae.thingsflow.common.Resource
import com.nosorae.thingsflow.common.SingleLiveData
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.domain.use_case.DeleteIssuesUseCase
import com.nosorae.thingsflow.domain.use_case.GetIssuesUseCase
import com.nosorae.thingsflow.domain.use_case.InsertIssuesUseCase
import com.nosorae.thingsflow.domain.use_case.LoadIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueListViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase,
    private val insertIssuesUseCase: InsertIssuesUseCase,
    private val loadIssuesUseCase: LoadIssuesUseCase,
    private val deleteIssuesUseCase: DeleteIssuesUseCase,
    private val pref: SharedPreferences
): ViewModel() {

    private val _issues = SingleLiveData<List<Issue>>()
    val issues: SingleLiveData<List<Issue>> get() = _issues

    private val _errorMessage = SingleLiveData<String>()
    val errorMessage: SingleLiveData<String> get() = _errorMessage

    private val _cachedIssues = SingleLiveData<List<Issue>>()
    val cachedIssues: SingleLiveData<List<Issue>> get() = _cachedIssues


    init {
        loadIssues()
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

    fun clearAndInsertIssues(issues: List<Issue>) {
        viewModelScope.launch {
            deleteIssuesUseCase()
            insertIssuesUseCase(issues)
        }
    }

    fun loadIssues() {
        loadIssuesUseCase().onEach { list ->
            _cachedIssues.value = list
        }.launchIn(viewModelScope)
    }


    fun savePrefString(key: String, value: String) {
        pref.edit()
            .putString(key, value)
            .apply()
    }

    fun getPrefString(key: String, defaultValue: String) {
        pref.getString(key, defaultValue).toString()
    }





}