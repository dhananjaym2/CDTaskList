package com.cdtasklist.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cdtasklist.utility.AppLog;
import com.cdtasklist.utility.LruBitmapCache;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ApplicationDelegate.java is Application's delegate class
 * <p/>
 * Created by dhananjay on 5/11/15.
 */
public class ApplicationDelegate extends Application {

    public static ApplicationDelegate ApplicationDelegateInstance = null;
    private static final String LOG_TAG = ApplicationDelegate.class.getSimpleName();
//    public static com.nostra13.universalimageloader.core.ImageLoader mImageloader = null;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationDelegateInstance = this;
//        getImageLoader_UIL(this);
    }

    /**
     * Method to initialize an object
     *
     * @return application object
     */
    public static synchronized ApplicationDelegate getApplicationInstance(/*Context mContext*/) {

        if (ApplicationDelegateInstance == null) {
            ApplicationDelegateInstance = new ApplicationDelegate();
        }

        return ApplicationDelegateInstance;
    }


//    /**
//     * Returns singleton class instance
//     */
//    public static com.nostra13.universalimageloader.core.ImageLoader getImageLoader_UIL(Context mContext) {
//        if (mImageloader == null) {
//            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                    // added following 3 lines
//                    .showImageForEmptyUri(R.drawable.rectangle_transparent)
//                    .showImageOnFail(R.drawable.rectangle_transparent)
//                    .showImageOnLoading(R.drawable.rectangle_transparent)
//                    .considerExifParams(true)
//                    .cacheInMemory(true)
//                    .cacheOnDisk(true)
//                    .build();
//
//            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
//                    .defaultDisplayImageOptions(defaultOptions)
//                    .build();
//            com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
//
//            mImageloader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
//
//        }
//
//        return mImageloader;
//    }

    /**
     * Method to Convert InputStream to String
     *
     * @param is InputStream
     * @return string
     * @throws Exception
     */
    public String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

//    /**
//     * Method to add model class object in ArrayList
//     *
//     * @param mArrPostParam ArrayList<LK_PostModel>
//     * @param key           String
//     * @param Value         String
//     * @param ParamType     String
//     * @return ArrayList
//     */
//    public ArrayList<LK_PostModel> LK_setPostParams(ArrayList<LK_PostModel> mArrPostParam, String
//            key, String Value, String ParamType) {
//
//        LK_PostModel mLK_PostModel = new LK_PostModel();
//
//        mLK_PostModel.setStr_PostParamKey(key);
//
//        mLK_PostModel.setObj_PostParamValue(Value);
//
//        mLK_PostModel.setStr_PostParamType(ParamType);
//
//        mArrPostParam.add(mLK_PostModel);
//
//        return mArrPostParam;
//    }

//    /**
//     * Method to add model class object in ArrayList
//     *
//     * @param mArrPostParam ArrayList<LK_PostModel>
//     * @param key           String
//     * @param Value         Integer
//     * @param ParamType     String
//     * @return ArrayList
//     */
//    public ArrayList<LK_PostModel> LK_setPostParams(ArrayList<LK_PostModel> mArrPostParam, String
//            key, Integer Value, String ParamType) {
//
//        LK_PostModel mLK_PostModel = new LK_PostModel();
//
//        mLK_PostModel.setStr_PostParamKey(key);
//
//        mLK_PostModel.setObj_PostParamValue(Value);
//
//        mLK_PostModel.setStr_PostParamType(ParamType);
//
//        mArrPostParam.add(mLK_PostModel);
//
//        return mArrPostParam;
//    }

//    /**
//     * Method to add model class object in ArrayList
//     *
//     * @param mArrPostParam ArrayList<LK_PostModel>
//     * @param key           String
//     * @param Value         Bitmap
//     * @param ParamType     String
//     * @return ArrayList
//     */
//    public ArrayList<LK_PostModel> LK_setPostParams(ArrayList<LK_PostModel> mArrPostParam, String
//            key, Bitmap Value, String ParamType) {
//
//        LK_PostModel mLK_PostModel = new LK_PostModel();
//
//        mLK_PostModel.setStr_PostParamKey(key);
//
//        mLK_PostModel.setObj_PostParamValue(Value);
//
//        mLK_PostModel.setStr_PostParamType(ParamType);
//
//        mArrPostParam.add(mLK_PostModel);
//
//        return mArrPostParam;
//    }

    /**
     * For Volley
     */

//    public static synchronized ApplicationDelegate getInstance() {
////        return ApplicationDelegateInstance;
//        return getApplicationInstance();
//    }
//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            if (getApplicationInstance() != null) {
//                AppLog.d(LOG_TAG, "getApplicationInstance() returns NOT null");
//                if (Volley
//                        .newRequestQueue
//                                (
//                                        getApplicationInstance()) != null) {
//                    AppLog.d(LOG_TAG, "Volley.newRequestQueue() returns NOT null");
//                    mRequestQueue =
//                            Volley
//                                    .newRequestQueue
//                                            (
//                                                    getApplicationInstance());
//                } else {
//                    AppLog.e(LOG_TAG, "Volley.newRequestQueue() returns null");
//                }
//            } else {
//                AppLog.e(LOG_TAG, "getApplicationContext() returns null");
//            }
//        }
//
//        return mRequestQueue;
//    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

//    public <T> void addToRequestQueue(Request<T> req, String tag) {
//        // set the default tag if tag is empty
//        req.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
//        getRequestQueue().add(req);
//    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(LOG_TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        AppLog.v(LOG_TAG, "in cancelPendingRequests() of volley tag:" + tag);
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}