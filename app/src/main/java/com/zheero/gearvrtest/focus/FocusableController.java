package com.zheero.gearvrtest.focus;

import com.zheero.gearvrtest.util.GazeController;

import org.gearvrf.GVRSceneObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zheERo on 2017/8/15.
 */

public final class FocusableController {
    /**
     * process方法是在onInside方法中调用的，调用频率非常高，所以这里使用CopyOnWriteArrayList，写时复制列表
     * 当往列表中添加元素时，先复制到前列表，往复制后的列表中添加元素，然后再将原来的列表指向复制后的列表，可以并发的读写
     */
    public static CopyOnWriteArrayList<FocusableSceneObject> interactiveObjects = new CopyOnWriteArrayList<>();

    public static void process(GVRSceneObject sceneObject){
        ArrayList<FocusableSceneObject> needToDisableFocus = new ArrayList<>();

        for (FocusableSceneObject fo : interactiveObjects){
            needToDisableFocus.add(fo);
        }

        if (sceneObject == null){
            GazeController.disableInteractiveCursor();
        }else {
            FocusableSceneObject fo = (FocusableSceneObject) sceneObject;
            fo.setFocus(true);
            fo.dispatchInFocus();
            needToDisableFocus.remove(fo);
        }

        for (FocusableSceneObject fo : needToDisableFocus){
            fo.setFocus(false);
        }
    }
}
