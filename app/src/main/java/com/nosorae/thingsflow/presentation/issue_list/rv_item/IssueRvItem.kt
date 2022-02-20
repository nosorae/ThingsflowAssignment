package com.nosorae.thingsflow.presentation.issue_list.rv_item

import android.view.View
import com.nosorae.thingsflow.R
import com.nosorae.thingsflow.databinding.RvItemIssueBinding
import com.nosorae.thingsflow.domain.model.Issue
import com.xwray.groupie.viewbinding.BindableItem



class IssueRvItem(
    private val data: Issue,
    private val onClickItem: (Issue) -> Unit
): BindableItem<RvItemIssueBinding>() {
    override fun bind(binding: RvItemIssueBinding, pos: Int) {
        with(binding) {
            tvIssue.text = data.run { "#$number : $title" }

            root.setOnClickListener {
                onClickItem(data)
            }
        }

    }

    override fun getLayout(): Int = R.layout.rv_item_issue

    override fun initializeViewBinding(view: View): RvItemIssueBinding
            = RvItemIssueBinding.bind(view)
}