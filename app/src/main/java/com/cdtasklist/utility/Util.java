package com.cdtasklist.utility;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cdtasklist.R;

/**
 * Utility functions for the complete app
 * <p/>
 * Created by dhananjay on 11-09-2015.
 */
public class Util {

    public static Dialog mDialog;
    public static ProgressDialog mProgressDialog;
    private static final String LOG_TAG = Util.class.getSimpleName();

    /**
     * method to check if internet is connected or not
     *
     * @param mContext Context
     * @return true if connected else false
     */
    public static boolean isInternetAvailable(Context mContext) {

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.
                CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    /**
     * Method to show toast message
     *
     * @param mContext        Context
     * @param strToastMessage String to show as Toast
     * @param isDurationLong  boolean duration of Toast. true for LENGTH_LONG or false for LENGTH_SHORT
     */
    public static void showToast(Context mContext, String strToastMessage, boolean isDurationLong) {
        if (mContext != null)
            if (strToastMessage != null)
                if (isDurationLong)
                    Toast.makeText(mContext, strToastMessage, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(mContext, strToastMessage, Toast.LENGTH_SHORT).show();
            else
                AppLog.e(LOG_TAG, "strToastMessage null in showToast()");
        else
            AppLog.e(LOG_TAG, "mContext null in showToast()");
    }

    public static void showAlert(final Context mContext, String strAlertMessage,
                                 String strBtnText, boolean isCancellable, View.OnClickListener
                                         mOnClickListener) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }
        mDialog = new Dialog(mContext, R.style.Dialog_No_Border);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.alert_no_header);
        mDialog.setCancelable(isCancellable);
        TextView txtAlertText_no_header = (TextView) mDialog.findViewById(R.id.txtAlertText_no_header);
        Button btnAlert_no_header = (Button) mDialog.findViewById(R.id.btnAlert_no_header);
        if (mOnClickListener != null) {
            btnAlert_no_header.setOnClickListener(mOnClickListener);
        } else {
            btnAlert_no_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
        }
        txtAlertText_no_header.setText(strAlertMessage);
        if (strBtnText != null) {
            btnAlert_no_header.setText(strBtnText);
        }
        try {
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * static method to SHOW progress dialog for the App.
     * <p/>
     * use Utility.hideProgressDialog(mContext)to hide this dialog.
     *
     * @param mContext                   Context
     * @param strMessageOnProgressDialog String Message On Progress Dialog
     */
    public static void showProgressDialog(Context mContext, String strMessageOnProgressDialog) {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {

                /**
                 * to change the message showing on mProgressDialog
                 */
                if (strMessageOnProgressDialog != null) {
                    mProgressDialog.setMessage(strMessageOnProgressDialog);
                } else {
                    try {
                        mProgressDialog.setMessage(mContext.getString(R.string.LoadingProgressDialog));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return;
//                mProgressDialog.dismiss();
            }
        }
//        mProgressDialog = new ProgressDialog(mContext);
//        mProgressDialog.setMessage(strLargeText);
//        mProgressDialog.setIndeterminate(false);
//        mProgressDialog.setCancelable(false);
//        mProgressDialog.show();
//        if (mProgressDialog == null)
        else
            mProgressDialog = new ProgressDialog(mContext);
        if (strMessageOnProgressDialog != null) {
            mProgressDialog.setMessage(strMessageOnProgressDialog);
        } else {
            try {
                mProgressDialog.setMessage(mContext.getString(R.string.LoadingProgressDialog));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mProgressDialog.setCancelable(false);
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();// Window$BadTokenException

            mProgressDialog = null;
            mProgressDialog = new ProgressDialog(mContext);
            if (strMessageOnProgressDialog != null) {
                mProgressDialog.setMessage(strMessageOnProgressDialog);
            } else {
                try {
                    mProgressDialog.setMessage(mContext.getString(R.string.LoadingProgressDialog));
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
            mProgressDialog.setCancelable(false);
            try {
                mProgressDialog.show();
            } catch (Exception ex) {
                e.printStackTrace();
            }
        }
    }

    /**
     * static method to HIDE progress dialog for the App.
     */
    public static void hideProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}