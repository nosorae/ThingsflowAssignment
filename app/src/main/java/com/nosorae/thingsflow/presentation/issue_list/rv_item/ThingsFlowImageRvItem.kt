package com.nosorae.thingsflow.presentation.issue_list.rv_item

import android.view.View
import com.bumptech.glide.Glide
import com.nosorae.thingsflow.R
import com.nosorae.thingsflow.common.Constants.THINGS_FLOW_IMAGE_URL
import com.nosorae.thingsflow.databinding.RvItemThingsFlowImageBinding
import com.xwray.groupie.viewbinding.BindableItem



class ThingsFlowImageRvItem(
    private val onClickItem: () -> Unit
): BindableItem<RvItemThingsFlowImageBinding>() {
    override fun bind(binding: RvItemThingsFlowImageBinding, pos: Int) {
        with(binding) {
            Glide.with(ivThingsFlow.context)
                .load(THINGS_FLOW_IMAGE_URL)
                .into(ivThingsFlow)

            root.setOnClickListener {
                onClickItem()
            }
        }
    }

    override fun getLayout(): Int = R.layout.rv_item_things_flow_image

    override fun initializeViewBinding(view: View): RvItemThingsFlowImageBinding
            = RvItemThingsFlowImageBinding.bind(view)
}