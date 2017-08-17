package com.zheero.gearvrtest;

import com.zheero.gearvrtest.focus.PickHandler;
import com.zheero.gearvrtest.util.GazeController;
import com.zheero.gearvrtest.video.VideoItem;
import com.zheero.gearvrtest.video.VideoScene;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRMaterial;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRRenderData;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.animation.GVRAnimator;
import org.gearvrf.animation.GVRRepeatMode;

import java.util.ArrayList;

/**
 * Created by zheERo on 2017/8/11.
 */

public class TestMain extends GVRMain {

    VideoScene scene;
    PickHandler pickHandler;
    GVRPicker gvrPicker;
    @Override
    public void onInit(GVRContext gvrContext) throws Throwable {
        super.onInit(gvrContext);
        scene = new VideoScene(gvrContext);
        GazeController.setupGazeCursor(gvrContext);

        gvrContext.runOnGlThreadPostRender(64, new Runnable() {
            @Override
            public void run() {
                setMainScene(scene);
                GazeController.enableGaze();
            }
        });

        pickHandler = new PickHandler();
        gvrPicker = new GVRPicker(gvrContext, scene);
    }

    public void setMainScene(GVRScene newScene){
        GVRScene oldScene = getGVRContext().getMainScene();
        oldScene.getEventReceiver().removeListener(pickHandler);
        oldScene.getMainCameraRig().getHeadTransformObject().detachComponent(GVRPicker.getComponentType());
        newScene.getEventReceiver().addListener(pickHandler);
        gvrPicker = new GVRPicker(getGVRContext(), newScene);
        newScene.getMainCameraRig().getHeadTransformObject().attachComponent(gvrPicker);
        getGVRContext().setMainScene(newScene);
    }

    public void onTap() {
        if (scene != null){
            scene.onTap();
        }
    }

    public void onPause(){
        if (scene != null){
            scene.onPause();
        }
    }

    public void onStop(){
        if (scene != null){
            scene.onStop();
        }
    }
}
