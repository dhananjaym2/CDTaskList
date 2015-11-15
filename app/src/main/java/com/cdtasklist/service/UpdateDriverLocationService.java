package com.cdtasklist.service;

//import com.google.android.gms.common.GooglePlayServicesClient;

public class UpdateDriverLocationService
//        extends Service implements
//        GooglePlayServicesClient.ConnectionCallbacks,
//        GooglePlayServicesClient.OnConnectionFailedListener,
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener,
//        GPSCallback, OnReceiveServerResponse
{

//    public static OnReceiveServerResponse mGetApiResult;
//    private String token_no;
//    private Context mContext;
//    private long index = 0, MIN_TIME_BW_UPDATES = 1000 * 10;
//    private String logTag = "UpdateDriver";
//
//    private GPSManager mGPSManager;
//
//    private LMSCSharedPreferences prefs;
//    private LMSCApplication mLMSC_application;
//    private int distance = 0, distanceDriver = 0;
//
//    private String mySpeed = "", toastText = "";
//
//    private double myLat = -1.0, myLng = -1.0;
//    private Handler mHandler;
//    private boolean isGPSinitialised, stopTracking;
//    private Handler mHandler_timer;
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Utility.d(logTag, "Service on create");
//
//        mContext = UpdateDriverLocationService.this;
//
//        mGPSManager = new GPSManager(mContext, LMSCConstants.MIN_GPS_UPDATE_TIME, LMSCConstants.
//                MIN_GPS_UPDATE_DISTANCE);
//
//        prefs = new LMSCSharedPreferences(mContext, null);
//
//        mLMSC_application = ((ApplicationDelegate) mContext
//                .getApplicationContext());
//
//        isGPSinitialised = false;
//
//        callApiUpdateDriverLocation(mContext);
//        setHandler();
//
//        toastText = "Tracking started.";
//
//        // dj added following to update location after every LMSCConstants.MIN_GPS_UPDATE_TIME
//        mHandler.sendEmptyMessage(1);
//        mHandler_timer = new Handler();
//        final Runnable mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if (mHandler_timer != null) {
//                        Utility.d(logTag, "mHandler_timer not null.. before callApiUpdateDriverLocation()");
//                        callApiUpdateDriverLocation(mContext);
//                        mHandler_timer.postDelayed(this, LMSCConstants.MIN_GPS_UPDATE_TIME);
//                    } else {
//                        Utility.d(logTag, "mHandler_timer null");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        mHandler_timer.postDelayed(mRunnable, LMSCConstants.MIN_GPS_UPDATE_TIME);
//    }
//
//    private void callApiUpdateDriverLocation(Context mContext) {
//        Utility.d(logTag, "Service callApiUpdateDriverLocation");
//        try {
//            if (Utility.isInternetAvailable(mContext)) {
//
//                myLat = LMSCConstants.myCurrentLat;
//                myLng = LMSCConstants.myCurrentLng;
//                Utility.d(logTag, "  Lat : " + myLat + " ,  lng : " + myLng);
//                if (myLat != -1.0 && myLng != -1.0
//                        && myLat != 0.0 && myLng != 0.0) {
//                    requestAsync_GetOffersNearBy();
//                } else {
//                    Utility.d(logTag, "Null Driver Lat : " + myLat + " , Driver lng : " + myLng);
//
//                }
//            }
//
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
//    }
//
//    private void requestAsync_GetOffersNearBy() {
//
//        try {
//            LMSC_ArrayConstants.mLMSC_PostArrayList = new ArrayList<>();
//
////            prefs = new LMSCSharedPreferences(mContext, null);
//            token_no = prefs.getStringValue(ServerRequestConstants.token_no, null);
//
//            Utility.debugLog(1, logTag, "token_no from lmscSharedPreferences:" +
//                    token_no);
//
//            mLMSC_application.LMSC_setPostParams(LMSC_ArrayConstants.mLMSC_PostArrayList,
//                    ServerRequestConstants.token_no, token_no, ServerRequestConstants.Key_PostStrValue);
//
//
//            double user_lat = LMSCConstants.myCurrentLat;
//            double user_lng = LMSCConstants.myCurrentLng;
//
//            if (user_lat != 0.0 && user_lng != 0.0) {
//
//                mLMSC_application.LMSC_setPostParams(LMSC_ArrayConstants.mLMSC_PostArrayList,
//                        ServerRequestConstants.user_lat, "" + user_lat, ServerRequestConstants.
//                                Key_PostStrValue);
//
//                mLMSC_application.LMSC_setPostParams(LMSC_ArrayConstants.mLMSC_PostArrayList,
//                        ServerRequestConstants.user_lng, "" + user_lng, ServerRequestConstants.
//                                Key_PostStrValue);
//            } else {
//                Utility.d(logTag, "INVALID user_lat:" + user_lat + " user_lng:" + user_lng);
//                return;
//            }
//
//            String strUrl_getoffersnearby = mContext.getString(R.string.LMSC_BaseUrl) + mContext.
//                    getString(R.string.getoffersnearby);
//            Utility.d(logTag, "strUrl_getoffersnearby:" + strUrl_getoffersnearby);
//
//            OnReceiveServerResponse mOnReceiveServerResponse = this;
//
//            BackgroundAsync mLmsc_postasync = new BackgroundAsync(mContext, mOnReceiveServerResponse,
//                    ServerRequestConstants.API_GetOffersNearBy, strUrl_getoffersnearby,
//                    LMSC_ArrayConstants.mLMSC_PostArrayList, false);
//            mLmsc_postasync.execute();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        super.onStartCommand(intent, flags, startId);
////        isServiceRunning = true;
//        initGPS();
//        return Service.START_STICKY;
//    }
//
//    private void stopService() {
//        Utility.d(logTag, "On stop service");
////        isServiceRunning = false;
//        toastText = "stop service start.";
//        mHandler.sendEmptyMessage(1);
//        mGPSManager.stopListening(mContext);
//        mGPSManager.setGPSCallback(null);
//        stopServiceNow();
//        toastText = "stop service end.";
//        mHandler.sendEmptyMessage(1);
//    }
//
//    private void stopServiceNow() {
//        stopSelf();
////        isServiceRunning = false;
//        mHandler.sendEmptyMessage(4);
//        Utility.d(logTag, "service stopped.");
//    }
//
//    @Override
//    public void onGPSUpdate(Location location) {
//        if (location != null && location.getLatitude() != 0.0
//                | location.getLongitude() != 0.0) {
////            double old_lat = LMSCConstants.myCurrentLat;
////            double old_lng = LMSCConstants.myCurrentLng;
//
//            LMSCConstants.myCurrentLat = location.getLatitude();
//            LMSCConstants.myCurrentLng = location.getLongitude();
//            myLat = location.getLatitude();
//            myLng = location.getLongitude();
//            LMSCConstants.currentLocation = new LatLng(location.getLatitude(),
//                    location.getLongitude());
////            Constants.lat_long = Constants.myCurrentLat + ","
////                    + Constants.myCurrentLng;
//
//            mySpeed = String.format("%.2f", location.getSpeed());
//
//            Utility.d(logTag, "Service onGPSUpdate = "
//                    + location.getLatitude() + "," + location.getLongitude() + "AND mySpeed : " +
//                    mySpeed);
//// now following api is called in every 3 minutes
////            if (old_lat != LMSCConstants.myCurrentLat || old_lng != LMSCConstants.myCurrentLng) {
////            callApiUpdateDriverLocation(mContext);
////                Utility.showToast(mContext, "api called to update location");
////            }
//            mHandler.sendEmptyMessage(1);
//        }
//    }
//
//    private void initGPS() {
//        if (!isGPSinitialised) {
//            if (mContext == null /*&& !mGPSManager.isGPSEnabled(mContext)*/) {
////                mContext.startActivity(new Intent(
////                        Settings.ACTION_LOCATION_SOURCE_SETTINGS));
////                showSettingsAlert();
////                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                mContext.startActivity(intent);
//            } else {
//                Handler hndl = new Handler();
//                hndl.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mGPSManager != null) {
//                            boolean isNetwork_GPSManager = mGPSManager.isNetworkEnabled(mContext);
//                            boolean isGps_GPSManager = mGPSManager.isGPSEnabled(mContext);
//                            if (isNetwork_GPSManager || isGps_GPSManager) {
//                                mGPSManager.startListening(mContext);
//                                mGPSManager.setGPSCallback(UpdateDriverLocationService.this);
//                                Utility.d(logTag, "setGPSCallback.");
//                            } else {
//                                Utility.d(logTag,
//                                        "mGPSManager.isNetworkEnabled() isGPSEnabled() both FALSE in Service");
////                                Utility.showToast(mContext, getString(R.string.turnOnGps));
//                            }
//                        } else {
//                            Utility.d(logTag, "mGPSManager NULL in Service");
////                            Utility.showToast(mContext, getString(R.string.
////                                    UnableToGetDestinationLocation));
//                        }
//                    }
//                }, 4000);
//            }
//            isGPSinitialised = true;
//        }
//    }
//
//    @SuppressLint("HandlerLeak")
//    private void setHandler() {
//        mHandler = new Handler() {
//
//            @Override
//            public void dispatchMessage(Message msg) {
//                if (msg.what == 1) {
////                    if (mContext != null && !toastText.equalsIgnoreCase(""))
////                        Whistle_CustomToast.showCustomToast(mContext, "" + toastText);
//                } else if (msg.what == 4) {
////                    if (mContext != null)
////                        Whistle_CustomToast.showCustomToast(mContext,
////                                "Tracking stopped.");
//                }
//            }
//        };
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult arg0) {
//        Utility.d(logTag, "TrackingService GPS onConnectionFailed");
//    }
//
//    @Override
//    public void onConnected(Bundle arg0) {
//        Utility.d(logTag, "TrackingService  onConnected");
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        Utility.d(logTag, "TrackingService GPS onConnectionSuspended");
//
//    }
//
////    @Override
////    public void onDisconnected() {
////        Utility.d(logTag, "TrackingService GPS onDisconnected");
////    }
//
//    @Override
//    public void setOnReceiveResult(String ApiName, String ApiResult) {
//        if (ApiName.equals(ServerRequestConstants.API_GetOffersNearBy)) {
//            if (ApiResult != null) {
//                LMSCParser mLmscParser = new LMSCParser(mContext, ApiResult);
//                // TODO parse response here and save in static arrayList
////                mLmscParser.getOffersResponseParsing(al_Offers_static);
//            } else {
//                Utility.d(logTag, "ApiResult null for:" + ApiName);
//            }
//
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mGPSManager.stopListening(mContext);
//        mGPSManager.setGPSCallback(null);
//        mGPSManager = null;
//        mHandler_timer = null;
//    }
}
