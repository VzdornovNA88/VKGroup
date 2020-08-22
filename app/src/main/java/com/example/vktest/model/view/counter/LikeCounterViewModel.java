package com.example.vktest.model.view.counter;

import com.example.vktest.model.Likes;

public class LikeCounterViewModel extends CounterViewModel{

    private Likes mLikes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        this.mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}
