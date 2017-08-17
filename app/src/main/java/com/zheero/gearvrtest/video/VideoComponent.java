package com.zheero.gearvrtest.video;

import android.media.MediaPlayer;

import com.zheero.gearvrtest.R;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.GVRVideoSceneObject;

/**
 * Created by zheERo on 2017/8/17.
 */

public class VideoComponent extends GVRSceneObject {

    private GVRVideoSceneObject videoSceneObject;
    private GVRContext gvrContext;
    private MediaPlayer mediaPlayer;

    public static final float WIDTH = 4.5F;
    public static final float HEIGHT = 3F;

    public VideoComponent(GVRContext gvrContext, float width, float height) {
        super(gvrContext, width, height);
        this.gvrContext = gvrContext;
        createVideoSceneObject();
        getTransform().setPosition(0,0,-4f);
        mediaPlayer.start();
    }
    private void createVideoSceneObject(){
        mediaPlayer = MediaPlayer.create(gvrContext.getContext(), R.raw.tron);
        videoSceneObject = new GVRVideoSceneObject(gvrContext
                , WIDTH, HEIGHT, mediaPlayer, GVRVideoSceneObject.GVRVideoType.MONO);
        addChildObject(videoSceneObject);
    }

    public void playVideo(){
        mediaPlayer.start();
    }
    public void pauseVideo(){
        mediaPlayer.pause();
    }
    public void stopVideo(){
        mediaPlayer.stop();
    }
}

