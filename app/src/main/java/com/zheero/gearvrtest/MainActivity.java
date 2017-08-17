package com.zheero.gearvrtest;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRScene;

public class MainActivity extends GVRActivity {

    TestMain main;
    long lastDownTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = new TestMain();
        setMain(main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        main.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        main.onStop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            lastDownTime = event.getDownTime();
        }

        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            // check if it was a quick tap
            if (event.getEventTime() - lastDownTime < 200) {
                // pass it as a tap to the Main
                main.onTap();
            }
        }

        return true;
    }
}
