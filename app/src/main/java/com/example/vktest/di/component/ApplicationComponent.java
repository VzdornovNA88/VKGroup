package com.example.vktest.di.component;

import com.example.vktest.common.manager.NetworkManager;
import com.example.vktest.di.module.RestModule;
import com.example.vktest.di.module.ApplicationModule;
import com.example.vktest.di.module.ManagerModule;
import com.example.vktest.model.view.CommentBodyViewModel;
import com.example.vktest.model.view.CommentFooterViewModel;
import com.example.vktest.model.view.InfoContactsViewModel;
import com.example.vktest.model.view.InfoLinksViewModel;
import com.example.vktest.model.view.TopicViewModel;
import com.example.vktest.mvp.presenter.BoardPresenter;
import com.example.vktest.mvp.presenter.CommentsPresenter;
import com.example.vktest.mvp.presenter.InfoContactsPresenter;
import com.example.vktest.mvp.presenter.InfoLinksPresenter;
import com.example.vktest.mvp.presenter.InfoPresenter;
import com.example.vktest.mvp.presenter.MainPresenter;
import com.example.vktest.mvp.presenter.MembersPresenter;
import com.example.vktest.mvp.presenter.NewsFeedPresenter;
import com.example.vktest.mvp.presenter.OpenedCommentPresenter;
import com.example.vktest.mvp.presenter.OpenedPostPresenter;
import com.example.vktest.mvp.presenter.TopicCommentsPresenter;
import com.example.vktest.ui.activity.BaseActivity;
import com.example.vktest.ui.activity.MainActivity;
import com.example.vktest.ui.fragment.CommentsFragment;
import com.example.vktest.ui.fragment.InfoContactsFragment;
import com.example.vktest.ui.fragment.InfoLinksFragment;
import com.example.vktest.ui.fragment.NewsFeedFragment;
import com.example.vktest.ui.fragment.OpenedCommentFragment;
import com.example.vktest.ui.fragment.OpenedPostFragment;
import com.example.vktest.ui.fragment.TopicCommentsFragment;
import com.example.vktest.ui.holder.ImageAttachmentHolder;
import com.example.vktest.ui.holder.NewsItemBodyHolder;
import com.example.vktest.ui.holder.NewsItemFooterHolder;
import com.example.vktest.ui.holder.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);
    void inject(MainActivity activity);
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(TopicCommentsFragment fragment);
    void inject(InfoLinksFragment fragment);
    void inject(InfoContactsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);
    void inject(InfoLinksViewModel.InfoLinksViewHolder holder);
    void inject(InfoContactsViewModel.InfoContactsViewHolder holder);

    //presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);
    void inject(InfoLinksPresenter presenter);
    void inject(InfoContactsPresenter presenter);

    //managers
    void inject(NetworkManager manager);
}
