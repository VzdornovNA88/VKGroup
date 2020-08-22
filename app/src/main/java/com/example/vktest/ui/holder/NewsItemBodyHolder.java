package com.example.vktest.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.common.utils.UiHelper;
import com.example.vktest.model.view.NewsItemBodyViewModel;
import com.example.vktest.ui.activity.BaseActivity;
import com.example.vktest.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    @BindView(R.id.tv_text)
    public TextView tvText;

    @BindView(R.id.tv_attachments)
    public TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;

    @Inject
    MyFragmentManager myFragmentManager;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        MyApplication.getApplicationComponent().inject(this);

        if (tvAttachments != null) {
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        tvText.setText(item.getText());
        tvAttachments.setText(item.getmAttachmentString());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFragmentManager.addFragment((BaseActivity) view.getContext(),
                        OpenedPostFragment.newInstance(item.getId()),
                        R.id.main_wrapper);

            }
        });
        UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
        UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getmAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
        itemView.setOnClickListener(null);
    }
}
