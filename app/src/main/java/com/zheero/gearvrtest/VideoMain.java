package com.zheero.gearvrtest;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRScene;
import org.gearvrf.scene_objects.GVRVideoSceneObject;
import org.gearvrf.scene_objects.GVRVideoSceneObjectPlayer;

import java.io.IOException;

/**
 * Created by zheERo on 2017/8/12.
 */

public class VideoMain extends GVRMain {

    private GVRScene scene;
    private GVRVideoSceneObject videoObject;
    private boolean isPlaying = false;

    @Override
    public void onInit(GVRContext gvrContext) throws Throwable {
        super.onInit(gvrContext);
        scene = gvrContext.getMainScene();
        scene.setBackgroundColor(0.8f, 0.8f, 0.8f, 0.8f);
        videoObject = createVideoObject(gvrContext);
        videoObject.getTransform().setPosition(0,0,-4);
        scene.addSceneObject(videoObject);
    }

    private GVRVideoSceneObject createVideoObject(GVRContext gvrContext) throws IOException {
        final AssetFileDescriptor afd = gvrContext.getActivity().getAssets().openFd("tron.mp4");
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        Uri uri = Uri.parse("");
        mediaPlayer.setDataSource(gvrContext.getContext(), uri);
        mediaPlayer.prepare();
        GVRVideoSceneObject video = new GVRVideoSceneObject(gvrContext, 8.0f,
                4.0f, mediaPlayer, GVRVideoSceneObject.GVRVideoType.MONO);
        video.setName("video");
        return video;
    }

    public void onPause(){
        if (videoObject != null){
            videoObject.getMediaPlayer().pause();
        }
    }

    public void onTap(){
        if (videoObject != null){
            if (!isPlaying){
                videoObject.getMediaPlayer().start();
            }else {
                videoObject.getMediaPlayer().pause();
            }
            isPlaying = !isPlaying;
        }
    }
}
