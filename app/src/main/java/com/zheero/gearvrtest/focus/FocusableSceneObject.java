package com.zheero.gearvrtest.focus;

import com.zheero.gearvrtest.util.GazeController;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRMeshCollider;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;

/**
 * Created by zheERo on 2017/8/14.
 */

public class FocusableSceneObject extends GVRSceneObject {

    private FocusListener mFocusListener = null;
    private boolean focus = false;
    public float[] hitLocation;
    public boolean showInteractiveCursor = true;

    public FocusableSceneObject(GVRContext gvrContext) {
        super(gvrContext);
        prepare(gvrContext);
    }
    public FocusableSceneObject(GVRContext gvrContext, GVRMesh gvrMesh, GVRTexture gvrTexture) {
        super(gvrContext, gvrMesh, gvrTexture);
        prepare(gvrContext);
    }

    public FocusableSceneObject(GVRContext gvrContext, float width, float height, GVRTexture t) {
        super(gvrContext, width, height, t);
        prepare(gvrContext);
    }

    private void prepare(GVRContext context){
        attachCollider(new GVRMeshCollider(context, true));
    }

    public void setFocus(boolean state){
        if (state == true && focus == false){//原来没有注视（focus为false），获得注视
            focus = true;
            this.dispatchGainedFocus();
            return;
        }
        if (state == false && focus == true){
            focus = false;
            this.dispatchLostFocus();
            return;
        }
    }

    public void setFocusListener(FocusListener mFocusListener) {
        this.mFocusListener = mFocusListener;
    }

    private void dispatchGainedFocus(){
        if (mFocusListener != null){
            mFocusListener.gainedFoucus(this);
        }
        if (showInteractiveCursor){
            GazeController.enableInteractiveCursor();
        }
    }

    public void dispatchInFocus(){
        if (mFocusListener != null){
            mFocusListener.inFoucus(this);
        }
        if (showInteractiveCursor) {
            GazeController.enableInteractiveCursor();
        }
    }

    private void dispatchLostFocus(){
        if (mFocusListener != null){
            mFocusListener.lostFocus(this);
        }
        if (showInteractiveCursor){
            GazeController.disableInteractiveCursor();
        }
    }
}
