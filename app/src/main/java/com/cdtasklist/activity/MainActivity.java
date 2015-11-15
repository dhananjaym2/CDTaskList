package com.cdtasklist.activity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cdtasklist.R;
import com.cdtasklist.adapter.RestaurantAdapter_List;
import com.cdtasklist.application.ApplicationDelegate;
import com.cdtasklist.constant.AppConstants;
import com.cdtasklist.gps.LocationGet;
import com.cdtasklist.models.RestaurantModel;
import com.cdtasklist.parser.AppParser;
import com.cdtasklist.utility.AppLog;
import com.cdtasklist.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private ListView listView_MainActivity;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ArrayList<RestaurantModel> al_RestaurantModel;
    private Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();

        LoadData();
    }

    View.OnClickListener alertClickListener_gps_internet = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Util.mDialog.dismiss();
            LoadData();
        }
    };


    public void LoadData() {
        if (!Util.isInternetAvailable(MainActivity.this)) {

            Util.showAlert(MainActivity.this, getString(R.string.NoInternetConnection), null,
                    false, alertClickListener_gps_internet);

        } else {

            if (isLocationEnabled()) {

                LocationGet locationGet = new LocationGet(this);
                userLocation = locationGet.getlocation();

                requestAsync_getRestaurantData();

            } else {
                Util.showAlert(MainActivity.this, getString(R.string.gps_enable_txt), null,
                        false, alertClickListener_gps_internet);
            }
        }
    }


    public boolean isLocationEnabled() {
        boolean isLocationEnabled;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        AppLog.v(LOG_TAG, "isLocationEnabled:" + isLocationEnabled);
        return isLocationEnabled;
    }

    private void initializeView() {
        listView_MainActivity = (ListView) findViewById(R.id.listView_MainActivity);
    }

    private void requestAsync_getRestaurantData() {

        Util.showProgressDialog(MainActivity.this, null);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                AppConstants.URL_getRestaurants,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        AppLog.d(LOG_TAG, response.toString());
                        Util.hideProgressDialog();

                        al_RestaurantModel = AppParser.parseAPIResponse_GetRestaurantsData(
                                MainActivity.this, response, userLocation);

                        if (al_RestaurantModel != null) {
                            RestaurantAdapter_List mRestaurantAdapter_List = new
                                    RestaurantAdapter_List(MainActivity.this, al_RestaurantModel);

                            AppLog.v(LOG_TAG, "before sorting al_RestaurantModel");
                            Collections.sort(al_RestaurantModel, RestaurantModel.distance_comparator);

                            AppLog.v(LOG_TAG, "before setting adapter on list view");
                            listView_MainActivity.setAdapter(mRestaurantAdapter_List);
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(LOG_TAG, "Error: " + error.getMessage());
                        // hide the progress dialog
                        Util.hideProgressDialog();
                        Util.showAlert(MainActivity.this, getString(R.string.OopsSomethingWentWrong)
                                , null, false, alertClickListener_gps_internet);
                    }
                });

// Adding request to request queue
        ApplicationDelegate.getApplicationInstance().addToRequestQueue(jsonObjReq, AppConstants.VOLLEY_CANCEL_REQUEST_TAG);
    }

}