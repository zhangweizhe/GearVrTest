package com.zheero.gearvrtest.focus;

/**
 * Created by zheERo on 2017/8/14.
 */

public interface FocusListener {
    /**
     * 场景对象获得焦点时会调用这个方法
     * @param object
     */
    void gainedFoucus(FocusableSceneObject object);

    /**
     * 场景对象失去焦点时会调用这个方法
     * @param object
     */
    void lostFocus(FocusableSceneObject object);

    /**
     * 焦点在场景对象中时会调用这个方法
     * @param object
     */
    void inFoucus(FocusableSceneObject object);
}
