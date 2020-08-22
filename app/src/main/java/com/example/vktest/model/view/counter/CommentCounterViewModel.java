package com.example.vktest.model.view.counter;

import com.example.vktest.model.Comments;

public class CommentCounterViewModel extends CounterViewModel{

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}
