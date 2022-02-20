package com.nosorae.thingsflow.presentation.issue_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nosorae.thingsflow.databinding.ActivityIssueDetailBinding

class IssueDetailActivity : AppCompatActivity() {

    private val viewModel: IssueDetailViewModel by viewModels()
    private lateinit var binding: ActivityIssueDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeIssueData()
    }

    private fun observeIssueData() {
        viewModel.issueData.observe(this) { data ->
            with(binding) {
                data?.let { issue ->
                    Glide.with(ivAvatar)
                        .load(issue.avatar_url)
                        .circleCrop()
                        .into(ivAvatar)

                    tvLogin.text = issue.login
                    tvBody.text = issue.body
                    title = "#${issue.number}"
                }
            }
        }
    }
}