package com.nosorae.thingsflow.presentation.issue_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nosorae.thingsflow.common.Constants.THINGS_FLOW_HOME_PAGE_URL
import com.nosorae.thingsflow.databinding.ActivityIssueListBinding
import com.nosorae.thingsflow.presentation.issue_detail.IssueDetailActivity
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueListActivity : AppCompatActivity() {

    private val viewModel: IssueListViewModel by viewModels()
    private lateinit var binding: ActivityIssueListBinding
    private val rvAdapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initRecyclerView() = with(binding) {
        rvIssueList.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initSearchTextView() = with(binding) {
        tvSearch.setOnClickListener {

        }
    }

    private fun showSearchInputDialogFragment() {

    }

    private fun openThingsFlowHomePageByWebBrowser() {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(THINGS_FLOW_HOME_PAGE_URL)
        ).also { intent ->
            startActivity(intent)
        }
    }

    private fun startIssueDetailActivity(number: String) {
        Intent(
            this,
            IssueDetailActivity::class.java
        ).also { intent ->
            startActivity(intent)
        }
    }

}