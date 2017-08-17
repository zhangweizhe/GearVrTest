package com.zheero.gearvrtest.util;

import com.zheero.gearvrtest.R;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;

/**
 * Created by zheERo on 2017/8/17.
 */

public class GazeController {

    private static GVRSceneObject cursor;
    private static GVRSceneObject highlightCursor;
    private static GVRContext gvrContext;

    private static float CURSOR_HIGH_OPACITY = 1.0f;
    private static float CURSOR_LOW_OPACITY = 0.0f;

    private static float NORMAL_CURSOR_SIZE = 0.25f;
    private static float HIGHLIGHT_CURSOR_SIZE = 0.25f;
    private static float CURSOR_Z_POSITION = -9.0f;

    private static int CURSOR_RENDER_ORDER = 100000;

    public static void  setupGazeCursor(GVRContext gvrContext){
        GazeController.gvrContext = gvrContext;
        cursor = new GVRSceneObject(gvrContext,
                gvrContext.createQuad(NORMAL_CURSOR_SIZE, NORMAL_CURSOR_SIZE),
                gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.head_tracker)));
        cursor.getTransform().setPositionZ(CURSOR_Z_POSITION);
        cursor.getRenderData().getMaterial().setOpacity(CURSOR_HIGH_OPACITY);
        cursor.getRenderData().setRenderingOrder(CURSOR_RENDER_ORDER);
        cursor.getRenderData().setDepthTest(false);

        highlightCursor = new GVRSceneObject(gvrContext,
                gvrContext.createQuad(HIGHLIGHT_CURSOR_SIZE, HIGHLIGHT_CURSOR_SIZE),
                gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.highlightcursor)));
        highlightCursor.getTransform().setPositionZ(CURSOR_Z_POSITION);
        highlightCursor.getRenderData().getMaterial().setOpacity(CURSOR_LOW_OPACITY);
        highlightCursor.getRenderData().setDepthTest(false);
        highlightCursor.getRenderData().setRenderingOrder(CURSOR_RENDER_ORDER);
    }

    public static void enableInteractiveCursor() {
        highlightCursor.getRenderData().getMaterial().setOpacity(CURSOR_HIGH_OPACITY);
        cursor.getRenderData().getMaterial().setOpacity(CURSOR_LOW_OPACITY);
    }

    public static void disableInteractiveCursor() {
        highlightCursor.getRenderData().getMaterial().setOpacity(CURSOR_LOW_OPACITY);
        cursor.getRenderData().getMaterial().setOpacity(CURSOR_HIGH_OPACITY);
    }

    public static void enableGaze(){
        if (highlightCursor.getParent() != null){
            disableGaze();
        }
        gvrContext.getMainScene().getMainCameraRig().addChildObject(cursor);
        gvrContext.getMainScene().getMainCameraRig().addChildObject(highlightCursor);
    }
    public static void disableGaze(){
        gvrContext.getMainScene().getMainCameraRig().removeChildObject(cursor);
        gvrContext.getMainScene().getMainCameraRig().removeChildObject(highlightCursor);
    }
}
