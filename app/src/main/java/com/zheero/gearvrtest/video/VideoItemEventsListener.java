package com.zheero.gearvrtest.video;

/**
 * Created by zheERo on 2017/8/15.
 */

public interface VideoItemEventsListener {

    void onFinishLoadVideoItem(VideoItem videoItem);
    boolean shouldVideoItemAppear(VideoItem videoItem);
}
