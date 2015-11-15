package com.cdtasklist.utility;

import android.content.Context;
import android.util.Log;

/**
 * Created by dhananjay on 23/8/15.
 */
public class AppLog {

    /**
     * App log for debugging
     *
     * @param type    int type of Log: 0(v), 1(d), 2(i), 3(w), 4(e), others' default(d)
     * @param TAG     String for Log Tag
     * @param message String for Log Message
     */
    public static void debugLog(int type, String TAG, String message) {
        switch (type) {

            case 0:
                Log.v(TAG, message);
                break;

            case 1:
                Log.d(TAG, message);
                break;

            case 2:
                Log.i(TAG, message);
                break;

            case 3:
                Log.w(TAG, message);
                break;

            case 4:
                Log.e(TAG, message);
                break;

            default:
                Log.d(TAG, message);
                break;
        }
    }

    /**
     * method for debug log equivalent to Log.v()
     * <p/>
     * VERBOSE
     *
     * @param logTag String for Log Tag
     * @param logMsg String for Log Message
     */
    public static void v(String logTag, String logMsg) {
        AppLog.debugLog(0, logTag, logMsg);
    }

    /**
     * method for debug log equivalent to Log.d()
     * <p/>
     * DEBUG
     *
     * @param logTag String for Log Tag
     * @param logMsg String for Log Message
     */
    public static void d(String logTag, String logMsg) {
        AppLog.debugLog(1, logTag, logMsg);
    }

    /**
     * method for debug log equivalent to Log.i()
     *
     * @param logTag String for Log Tag
     * @param logMsg String for Log Message
     */
    public static void i(String logTag, String logMsg) {
        AppLog.debugLog(2, logTag, logMsg);
    }

    /**
     * method for debug log equivalent to Log.w()
     * <p/>
     * WARN
     *
     * @param logTag String for Log Tag
     * @param logMsg String for Log Message
     */
    public static void w(String logTag, String logMsg) {
        AppLog.debugLog(3, logTag, logMsg);
    }

    /**
     * method for debug log equivalent to Log.e()
     * <p/>
     * ERROR
     *
     * @param logTag String for Log Tag
     * @param logMsg String for Log Message
     */
    public static void e(String logTag, String logMsg) {
        AppLog.debugLog(4, logTag, logMsg);
    }

    /**
     * method to show Log and Toast msg when Exception arises in parameter strMethodName
     *
     * @param mContext      Context object
     * @param strMethodName String MethodName where Exception arises
     * @param exception     Exception object
     */
    public static void showExceptionLogAndToast(Context mContext, String strMethodName, Exception
            exception) {
        String strExceptionMessage = "" + strMethodName + "() exception:" + exception;

//        Util.showToast(mContext, /*mContext.getString(R.string.OopsSomethingWentWrong) +*/
//                strExceptionMessage, true);

        AppLog.e("AppLog_EXCEPTION", strExceptionMessage);

        exception.printStackTrace();
    }

}