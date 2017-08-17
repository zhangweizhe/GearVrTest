package com.zheero.gearvrtest.shader;

import android.content.Context;

import com.zheero.gearvrtest.R;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRShaderTemplate;
import org.gearvrf.utility.TextFile;

/**
 * Created by zheERo on 2017/8/16.
 */

public class CutoutShader extends GVRShaderTemplate {

    public static final String TEXTURE_KEY = "texture";
    public static final String CUTOUT = "cutout";

    public CutoutShader(GVRContext gvrContext) {
        super("float cutout, sample2D texture");
        Context context = gvrContext.getContext();
        setSegment("FragmentTemplate", TextFile.readTextFile(context, R.raw.cutout_fragment));
        setSegment("VertexTemplate", TextFile.readTextFile(context, R.raw.cutout_vertex));
    }
}
