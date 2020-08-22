package com.example.vktest.model.view;

import android.view.View;
import android.widget.TextView;

import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.model.Place;
import com.example.vktest.model.Topic;
import com.example.vktest.ui.activity.BaseActivity;
import com.example.vktest.ui.fragment.TopicCommentsFragment;
import com.example.vktest.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewModel extends BaseViewModel {
    private int mId;
    private int mGroupId;
    private String mTitle;

    private String mCommentsCount;

    public TopicViewModel() {

    }

    public TopicViewModel(Topic topic) {
        this.mId = topic.getId();
        this.mGroupId = topic.getGroupId();

        this.mTitle = topic.getTitle();

        this.mCommentsCount = topic.getComments() + " сообщений";
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }



    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @BindView(R.id.tv_title)
        public TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        public TextView tvCommentsCount;

        @Inject
        MyFragmentManager mFragmentManager;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvCommentsCount.setText(topicViewModel.getCommentsCount());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) view.getContext(),
                            TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()), String.valueOf(topicViewModel.getId()))),
                            R.id.main_wrapper);
                }
            });
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);
        }
    }
}
