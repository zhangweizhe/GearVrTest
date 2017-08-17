package com.zheero.gearvrtest.loadComonent;

import com.zheero.gearvrtest.R;
import com.zheero.gearvrtest.focus.FocusListener;
import com.zheero.gearvrtest.focus.FocusableSceneObject;
import com.zheero.gearvrtest.shader.CutoutShader;
import com.zheero.gearvrtest.util.RenderingOrderApplication;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRDrawFrameListener;
import org.gearvrf.GVRMaterialShaderManager;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRShaderTemplate;
import org.gearvrf.GVRTexture;

/**
 * Created by zheERo on 2017/8/15.
 */

public class LoadComponent extends GVRSceneObject implements FocusListener {

    private static final int CUTOUT_VALUE = 1;
    private static final float LOAD_SPEED = 0.01f;
    private float valueFloatTexture;

    private GVRTexture circleAlphaTexture;
    private GVRTexture circleTexture;

    private GVRSceneObject circleAlpha;
    private FocusableSceneObject circle;

    private LoadComponentListener loadComponentListener;
    private GVRContext gvrContext;
    private GVRDrawFrameListener drawFrameListener;
    private boolean isLoading = false;

    public LoadComponent(GVRContext gvrContext, LoadComponentListener listener){
        super(gvrContext);
        loadComponentListener = listener;
        this.gvrContext = gvrContext;
        //异步加载贴图
        this.gvrContext.runOnGlThread(new Runnable() {
            @Override
            public void run() {
                loadTexture();
                createLoadComponent();
            }
        });
    }

    private void loadTexture(){
        circleAlphaTexture = gvrContext.getAssetLoader()
                .loadTexture(new GVRAndroidResource(gvrContext, R.drawable.loading_two__colors));
        circleTexture = gvrContext.getAssetLoader()
                .loadTexture(new GVRAndroidResource(gvrContext, R.drawable.loading));
    }

    private void createLoadComponent(){
        circleAlpha = new GVRSceneObject(gvrContext, gvrContext.createQuad(0.5f, 0.5f), circleAlphaTexture);
        circle = new FocusableSceneObject(gvrContext, gvrContext.createQuad(0.5f, 0.5f), circleTexture);

        circle.setFocusListener(this);
        circle.getRenderData().setRenderingOrder(RenderingOrderApplication.LOADING_COMPONENT);

        GVRMaterialShaderManager shaderManager = gvrContext.getMaterialShaderManager();
        GVRShaderTemplate shaderTemplate = shaderManager.retrieveShaderTemplate(CutoutShader.class);
        circleAlpha.getRenderData().getMaterial()
                .setTexture(CutoutShader.TEXTURE_KEY, circleAlphaTexture);
        circleAlpha.getRenderData().getMaterial()
                .setFloat(CutoutShader.CUTOUT, valueFloatTexture);
        circleAlpha.getRenderData().setRenderingOrder(RenderingOrderApplication.LOADING_COMPONENT);
        circleAlpha.getRenderData().getMaterial().setMainTexture(circleAlphaTexture);
        shaderTemplate.bindShader(gvrContext, circleAlpha.getRenderData(), gvrContext.getMainScene());
        circle.setName("circle");
        addChildObject(circle);
        addChildObject(circleAlpha);
    }

    public void setFloatTexture(){
        drawFrameListener = new GVRDrawFrameListener() {
            @Override
            public void onDrawFrame(float frameTime) {
                isLoading = true;
                valueFloatTexture += LOAD_SPEED;
                if (valueFloatTexture <= 1.0){
                    circleAlpha.getRenderData().getMaterial()
                            .setFloat(CutoutShader.CUTOUT, valueFloatTexture);
                }else {
                    finishLoadComponent();
                }
            }
        };
        gvrContext.registerDrawFrameListener(drawFrameListener);
    }

    public void disableLoadComponentListener(){
        gvrContext.unregisterDrawFrameListener(drawFrameListener);
    }

    private void finishLoadComponent(){
        gvrContext.unregisterDrawFrameListener(drawFrameListener);
        isLoading = false;
        gvrContext.getMainScene().removeSceneObject(this);

        loadComponentListener.onFinishLoadComponent();
    }

    @Override
    public void gainedFoucus(FocusableSceneObject object) {

    }

    @Override
    public void lostFocus(FocusableSceneObject object) {

    }

    @Override
    public void inFoucus(FocusableSceneObject object) {

    }
}
