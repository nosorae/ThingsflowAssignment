package com.nosorae.thingsflow.presentation.issue_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nosorae.thingsflow.common.Constants.LOG_TAG
import com.nosorae.thingsflow.common.Constants.PARAM_ISSUE_MODEL
import com.nosorae.thingsflow.domain.model.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
    // TODO 로컬 저장 유스케이스
): ViewModel() {

    private val _issueData = MutableLiveData<Issue?>(null)
    val issueData: LiveData<Issue?> = _issueData

    init {
        Log.e(LOG_TAG, "IssueDetailViewModel init 진입")
        savedStateHandle.get<Issue>(PARAM_ISSUE_MODEL)?.let { issue ->
            Log.e(LOG_TAG, "전달된 데이터 : $issue")
            _issueData.value = issue
        }
    }
}