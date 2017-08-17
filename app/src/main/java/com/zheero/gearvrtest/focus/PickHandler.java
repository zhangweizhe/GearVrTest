package com.zheero.gearvrtest.focus;

import android.util.Log;

import com.zheero.gearvrtest.util.GazeController;

import org.gearvrf.GVRPicker;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.IPickEvents;

/**
 * Created by zheERo on 2017/8/15.
 */

public class PickHandler implements IPickEvents {

    public GVRSceneObject pickedObject = null;

    @Override
    public void onPick(GVRPicker picker) {

    }

    @Override
    public void onNoPick(GVRPicker picker) {
        FocusableSceneObject fo = (FocusableSceneObject) pickedObject;
        if (fo != null)
        {
            fo.setFocus(false);
            org.gearvrf.utility.Log.v("PICKER", fo.getName() + " onNoPick");
        }
        pickedObject = null;
        GazeController.disableInteractiveCursor();
    }

    @Override
    public void onEnter(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject collision) {
        Log.i("info", "onEnter: ");
        if (sceneObj instanceof FocusableSceneObject){
            FocusableSceneObject fo = (FocusableSceneObject) sceneObj;
            pickedObject = fo;
            //获得注视
            fo.setFocus(true);
            //随后进入注视中状态
            fo.dispatchInFocus();
            fo.hitLocation = collision.getHitLocation();
        }
    }

    @Override
    public void onExit(GVRSceneObject sceneObj) {
        FocusableSceneObject fo = (FocusableSceneObject) pickedObject;
        if (fo != null){
            fo.setFocus(false);
        }
        pickedObject = null;
    }

    @Override
    public void onInside(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject collision) {
        FocusableController.process(sceneObj);
    }
}
