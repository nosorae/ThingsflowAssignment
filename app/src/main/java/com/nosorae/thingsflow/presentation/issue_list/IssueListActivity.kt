package com.nosorae.thingsflow.presentation.issue_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nosorae.thingsflow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_list)
    }
}