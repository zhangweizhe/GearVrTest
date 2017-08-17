package com.zheero.gearvrtest.video;

import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import com.zheero.gearvrtest.focus.FocusListener;
import com.zheero.gearvrtest.focus.FocusableSceneObject;
import com.zheero.gearvrtest.loadComonent.LoadComponent;
import com.zheero.gearvrtest.loadComonent.LoadComponentListener;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRMeshCollider;
import org.gearvrf.GVRTexture;

/**
 * Created by zheERo on 2017/8/15.
 */

public class VideoItem extends FocusableSceneObject implements FocusListener {
    
    private final String TAG = getClass().getSimpleName();
    private VideoItemEventsListener videoItemEventsListener;
    private LoadComponent loadComponent;
    
    public VideoItem(GVRContext gvrContext) {
        super(gvrContext);
        prepareViewItem(gvrContext);
    }

    public VideoItem(GVRContext gvrContext, GVRMesh gvrMesh, GVRTexture gvrTexture) {
        super(gvrContext, gvrMesh, gvrTexture);
        prepareViewItem(gvrContext);
    }

    public VideoItem(GVRContext gvrContext, float width, float height, GVRTexture t) {
        super(gvrContext, width, height, t);
        prepareViewItem(gvrContext);
    }

    private void prepareViewItem(GVRContext gvrContext){
        setFocusListener(this);
    }

    @Override
    public void gainedFoucus(FocusableSceneObject object) {
        Log.i(TAG, "gainedFoucus: ");
        if (videoItemEventsListener != null &&
                videoItemEventsListener.shouldVideoItemAppear(this) == true){
                createLoadComponent(getGVRContext());
        }
    }

    @Override
    public void lostFocus(FocusableSceneObject object) {
        Log.i(TAG, "lostFocus: ");
        if (this.loadComponent != null){
            this.loadComponent.disableLoadComponentListener();
            this.removeChildObject(loadComponent);
        }
    }

    @Override
    public void inFoucus(FocusableSceneObject object) {
        //Log.i(TAG, "inFoucus: ");
    }

    public void createLoadComponent(GVRContext gvrContext){
        loadComponent = new LoadComponent(gvrContext, new LoadComponentListener() {
            @Override
            public void onFinishLoadComponent() {
                Log.i(TAG, "load component onFinishLoadComponent: ");
                VideoItem.this.onFinishLoadComponent();
            }
        });
        this.addChildObject(loadComponent);

        loadComponent.setFloatTexture();
        loadComponent.getTransform().setPosition(0f, 0f, 0.11f);
    }

    public void setVideoItemEventsListener(VideoItemEventsListener listener){
        this.videoItemEventsListener = listener;
    }

    private void onFinishLoadComponent(){
        if (this.videoItemEventsListener != null){
            videoItemEventsListener.onFinishLoadVideoItem(this);
            removeChildObject(loadComponent);
        }
    }
}
