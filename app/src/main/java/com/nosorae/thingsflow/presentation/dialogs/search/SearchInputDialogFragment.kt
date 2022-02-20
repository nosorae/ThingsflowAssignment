package com.nosorae.thingsflow.presentation.dialogs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nosorae.thingsflow.R
import com.nosorae.thingsflow.databinding.DialogFragmentSearchInputBinding

class SearchInputDialogFragment(
    private val onClickSearchButton: (String, String) -> Unit
) : DialogFragment(R.layout.dialog_fragment_search_input) {

    private var binding: DialogFragmentSearchInputBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogFragmentSearchInputBinding = DialogFragmentSearchInputBinding.inflate(inflater, container, false)
        binding = dialogFragmentSearchInputBinding

        initSearchButton(dialogFragmentSearchInputBinding)
        return dialogFragmentSearchInputBinding.root

    }

    private fun initSearchButton(dialogFragmentSearchInputBinding: DialogFragmentSearchInputBinding) =
        with(dialogFragmentSearchInputBinding) {
            btSearch.setOnClickListener {
                val org = etOrg.text.toString()
                val repo = etRepo.text.toString()
                onClickSearchButton(org, repo)
                dismiss()
            }
        }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}