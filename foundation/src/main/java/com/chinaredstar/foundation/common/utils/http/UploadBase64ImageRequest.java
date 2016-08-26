package com.chinaredstarer.foundation.common.utils.http;

import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.chinaredstarer.foundation.interaction.bean.Result;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cody.yi on 2016/7/22.
 */
public class UploadBase64ImageRequest extends GsonRequest<Result> {

//    private Map<String, DataPart> mByteDatas;
    private final static String KEY_IMAGE = "image";
    private final static String KEY_NAME = "name";

    private String mImageName;
    private Bitmap mBitmap;
    private Map<String, String> mParams;

    public UploadBase64ImageRequest(String url,
                                    String imageName, Bitmap bitmap,
                                    Map<String, String> params,
                                    Response.Listener<Result> listener,
                                    Response.ErrorListener errorListener) {
        super(Method.POST, url, params, getType(Result.class, String.class), listener, errorListener);
        mImageName = imageName;
        mBitmap = bitmap;
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        //Converting Bitmap to String
        String image = getStringImage(mBitmap);

        //getting parameters
        Map<String,String> params = super.getParams();

        if (params == null) params = new HashMap<>();
        //Adding parameters
        params.put(KEY_IMAGE, image);
        params.put(KEY_NAME, mImageName);

        //returning parameters
        return params;
    }

    /**
     * convert Bitmap to base64 String.
     * @param bmp bitmap
     * @return base64
     */
    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
