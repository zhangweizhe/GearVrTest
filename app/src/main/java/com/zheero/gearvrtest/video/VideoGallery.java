package com.zheero.gearvrtest.video;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;

/**
 * Created by zheERo on 2017/8/16.
 */

public class VideoGallery extends GVRSceneObject {

    private GVRContext gvrContext;
    private GVRScene scene;


    public VideoGallery(GVRContext gvrContext, GVRScene scene) {
        super(gvrContext);
        this.gvrContext = gvrContext;
        this.scene = scene;
    }

    public VideoGallery(GVRContext gvrContext, GVRMesh mesh, GVRTexture texture) {
        super(gvrContext, mesh, texture);
        this.gvrContext = gvrContext;
        this.scene = scene;
    }


}
