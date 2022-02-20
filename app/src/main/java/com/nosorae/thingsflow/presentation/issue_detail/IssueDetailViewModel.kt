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
): ViewModel() {

    private val _issueData = MutableLiveData<Issue?>(null)
    val issueData: LiveData<Issue?> = _issueData

    init {
        savedStateHandle.get<Issue>(PARAM_ISSUE_MODEL)?.let { issue ->
            _issueData.value = issue
        }
    }
}