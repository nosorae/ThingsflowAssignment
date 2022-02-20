package com.nosorae.thingsflow.presentation.issue_list

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nosorae.thingsflow.R
import com.nosorae.thingsflow.common.Constants.PARAM_ISSUE_MODEL
import com.nosorae.thingsflow.common.Constants.PREF_REPO
import com.nosorae.thingsflow.common.Constants.THINGS_FLOW_HOME_PAGE_URL
import com.nosorae.thingsflow.databinding.ActivityIssueListBinding
import com.nosorae.thingsflow.domain.model.Issue
import com.nosorae.thingsflow.presentation.dialogs.error.ErrorDialogFragment
import com.nosorae.thingsflow.presentation.dialogs.search.SearchInputDialogFragment
import com.nosorae.thingsflow.presentation.issue_detail.IssueDetailActivity
import com.nosorae.thingsflow.presentation.issue_list.rv_item.IssueRvItem
import com.nosorae.thingsflow.presentation.issue_list.rv_item.ThingsFlowImageRvItem
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


        initTexts()

        initRecyclerView()
        initSearchTextView()

        observeIssuesData()
        observeErrorData()
        observeCachedIssuesData()
    }

    private fun initTexts() {
        title = getString(R.string.app_name)
        binding.tvSearch.text = "${viewModel.lastOrg}/${viewModel.lastRepo}"
    }

    //--------------------------------------------------------------------

    private fun initRecyclerView() = with(binding) {
        rvIssueList.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initSearchTextView() = with(binding) {
        tvSearch.setOnClickListener {
            showSearchInputDialogFragment()
        }
    }

    private fun showSearchInputDialogFragment() {
        SearchInputDialogFragment { org, repo ->
            viewModel.run {
                viewModel.savePrefString(PREF_REPO, repo)
                getIssues(org, repo)
            }
        }.show(supportFragmentManager, null)
    }

    //--------------------------------------------------------------------

    @SuppressLint("SetTextI18n")
    private fun observeIssuesData() {
        viewModel.issues.observe(this) { issues ->
            rvAdapter.clear()
            handleIssues(issues)
            viewModel.clearAndInsertIssues(issues)
            //viewModel.insertIssues(issues)
        }
    }

    private fun observeCachedIssuesData() {
        viewModel.cachedIssues.observe(this) { issues ->
            handleIssues(issues)
        }
    }
    private fun handleIssues(issues: List<Issue>) {
        rvAdapter.clear()
        issues.forEachIndexed { index, issue ->
            if (index == 4) {
                insertThingsFlowImageRvItem()
            } else {
                rvAdapter.add(
                    IssueRvItem(issue) { i ->
                        startIssueDetailActivity(i)
                    }
                )
            }
        }
        if (issues.size < 5) {
            insertThingsFlowImageRvItem()
        }
    }

    private fun insertThingsFlowImageRvItem() {
        rvAdapter.add(
            ThingsFlowImageRvItem() {
                openThingsFlowHomePageByWebBrowser()
            }
        )
    }

    private fun openThingsFlowHomePageByWebBrowser() {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(THINGS_FLOW_HOME_PAGE_URL)
        ).also { intent ->
            startActivity(intent)
        }
    }

    private fun startIssueDetailActivity(issue: Issue) {
        Intent(
            this,
            IssueDetailActivity::class.java
        )
            .also { intent ->
                intent.putExtra(PARAM_ISSUE_MODEL, issue)
                startActivity(intent)
            }
    }

    //--------------------------------------------------------------------

    private fun observeErrorData() {
        viewModel.errorMessage.observe(this) { message ->
            showErrorMessageDialog(message) // 인터넷이 안 될때의 에러메시지도 함께 가고 있습니다.
            rvAdapter.clear()
            viewModel.loadIssues()
        }
    }

    private fun showErrorMessageDialog(message: String) {
        ErrorDialogFragment(
            message = message
        ).show(supportFragmentManager, null)
    }




}