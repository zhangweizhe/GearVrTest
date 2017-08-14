package com.zheero.gearvrtest;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRMaterial;
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

    GVRScene scene;
    @Override
    public void onInit(GVRContext gvrContext) throws Throwable {
        super.onInit(gvrContext);
        scene = gvrContext.getMainScene();
        scene.setBackgroundColor(0.8f, 0.8f, 0.8f, 0.8f);

        GVRSceneObject aniObj = gvrContext.getAssetLoader().loadModel("plane.fbx", scene);
        GVRTexture texture = gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.ic_launcher));
        ArrayList<GVRRenderData> renderDatas = aniObj.getAllComponents(GVRRenderData.getComponentType());
        renderDatas.get(0).getMaterial().setTexture("diffuseTexture", texture);
        aniObj.getTransform().setPosition(0,0,-120);

        GVRAnimator animator = (GVRAnimator) aniObj.getComponent(GVRAnimator.getComponentType());
        animator.setRepeatCount(-1);
        animator.setRepeatMode(GVRRepeatMode.REPEATED);
        animator.start();

        scene.addSceneObject(aniObj);

    }
}
