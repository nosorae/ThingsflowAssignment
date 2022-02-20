package com.nosorae.thingsflow.presentation.dialogs.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nosorae.thingsflow.R
import com.nosorae.thingsflow.databinding.DialogFragmentErrorBinding

class ErrorDialogFragment(
    private val message: String,
) : DialogFragment(R.layout.dialog_fragment_error) {

    private var binding: DialogFragmentErrorBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogFragmentErrorBinding = DialogFragmentErrorBinding.inflate(inflater, container, false)
        binding = dialogFragmentErrorBinding
        isCancelable = false

        initErrorMessageTextView(dialogFragmentErrorBinding)
        initOkButton(dialogFragmentErrorBinding)
        return dialogFragmentErrorBinding.root

    }

    private fun initErrorMessageTextView(dialogFragmentErrorBinding: DialogFragmentErrorBinding) =
        with(dialogFragmentErrorBinding) {
            tvErrorMessage.text = message
        }


    private fun initOkButton(dialogFragmentErrorBinding: DialogFragmentErrorBinding) =
        with(dialogFragmentErrorBinding) {
            btOk.setOnClickListener {
                dismiss()
            }
        }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}