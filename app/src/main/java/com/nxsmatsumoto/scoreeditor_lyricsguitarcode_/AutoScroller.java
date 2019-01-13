package com.nxsmatsumoto.scoreeditor_lyricsguitarcode_;

import android.os.Handler;
import android.widget.ScrollView;

public class AutoScroller {

    private static final int DEFAULT_SCROLL_BY = 1;
    private static final int DURATION = 1;

    private final ScrollView scrollView;
    private final int scrollBy;

    public AutoScroller(ScrollView scrollView) {
        this(scrollView, DEFAULT_SCROLL_BY);
    }

    public AutoScroller(ScrollView scrollView, int scrollBy) {
        this.scrollView = scrollView;
        this.scrollBy = scrollBy;
    }

    private final Handler autoScrollHandler = new Handler();

    private final Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            scrollView.smoothScrollBy(scrollBy, DURATION);
            autoScrollHandler.postDelayed(this, DURATION);
        }
    };

    public void start() {
        autoScrollHandler.postDelayed(autoScrollRunnable, DURATION);
    }

    public void stop() {
        autoScrollHandler.removeCallbacksAndMessages(null);
    }
}
