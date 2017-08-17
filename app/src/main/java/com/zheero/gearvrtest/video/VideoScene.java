package com.zheero.gearvrtest.video;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.zheero.gearvrtest.R;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRBitmapTexture;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRTexture;

/**
 * Created by zheERo on 2017/8/15.
 */

public class VideoScene extends GVRScene {

    private GVRContext gvrContext;
    private boolean isPlaying = false;
    private VideoComponent videoComponent;

    /**
     * Constructs a scene with a camera rig holding left & right cameras in it.
     *
     * @param gvrContext {@link GVRContext} the app is using.
     */
    public VideoScene(GVRContext gvrContext) {
        super(gvrContext);
        this.gvrContext = gvrContext;
        setBackgroundColor(1,1,1,1);
        createVideoItem();
    }

    private void createVideoItem(){
        GVRTexture texture = gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.ic_launcher));
        VideoItem videoItem = new VideoItem(gvrContext, 4, 3, texture);
        videoItem.getTransform().setPosition(0,0,-5);
        addSceneObject(videoItem);
        videoItem.setVideoItemEventsListener(new VideoItemEventsListener() {
            @Override
            public void onFinishLoadVideoItem(VideoItem videoItem) {
                //videoItem.getTransform().setScale(1.1f, 1.1f,1);
                isPlaying = true;
                gvrContext.runOnGlThread(new Runnable() {
                    @Override
                    public void run() {
                        createVideoComponent();
                    }
                });
            }

            @Override
            public boolean shouldVideoItemAppear(VideoItem videoItem) {
                return !isPlaying;
            }
        });
    }

    private void createVideoComponent(){
        videoComponent = new VideoComponent(gvrContext,VideoComponent.WIDTH, VideoComponent.HEIGHT);
        this.addSceneObject(videoComponent);
    }

    public void onTap(){
        if (isPlaying){
            Log.i("", "onTap: ");
            isPlaying = false;
        }
    }
    public void onPause(){
        if (isPlaying){
            videoComponent.pauseVideo();
        }
    }

    public void onStop(){
        if (isPlaying){
            videoComponent.stopVideo();
        }
    }
}
