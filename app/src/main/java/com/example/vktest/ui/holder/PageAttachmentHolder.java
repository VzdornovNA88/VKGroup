package com.example.vktest.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.example.vktest.R;
import com.example.vktest.common.utils.Utils;
import com.example.vktest.model.view.PageAttachmentViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageAttachmentHolder extends BaseViewHolder<PageAttachmentViewModel> {
    @BindView(R.id.tv_title)
    public TextView title;

    public PageAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PageAttachmentViewModel pageAttachmentViewModel) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInActionView(pageAttachmentViewModel.getmUrl(), view.getContext());
            }
        });
        title.setText(pageAttachmentViewModel.getTitle());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
    }


}
