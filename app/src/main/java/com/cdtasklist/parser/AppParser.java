package com.cdtasklist.parser;

import android.content.Context;
import android.location.Location;

import com.cdtasklist.R;
import com.cdtasklist.models.RestaurantModel;
import com.cdtasklist.utility.AppLog;
import com.cdtasklist.utility.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for Parsing of API response
 * <p/>
 * Created by dhananjay on 5/11/15.
 */
public class AppParser {

    private static final String LOG_TAG = AppParser.class.getSimpleName();

    public static ArrayList<RestaurantModel> parseAPIResponse_GetRestaurantsData(Context mContext
            , JSONObject apiResultInJSON, Location userLocation) {
        try {
//            JSONObject jObjParent = new JSONObject(apiResultInJSON);
            if (apiResultInJSON.has("status")) {

                JSONObject jObj_status = apiResultInJSON.getJSONObject("status");
                if (jObj_status != null) {

                    if (jObj_status.has("rcode") && jObj_status.getInt("rcode") == 200) {

                        AppLog.v(LOG_TAG, "rcode:" + jObj_status.getInt("rcode"));

                        if (apiResultInJSON.has("data")) {

                            JSONObject jObj_data = apiResultInJSON.getJSONObject("data");

                            if (jObj_data != null) {
                                ArrayList<RestaurantModel> al_RestaurantModel = new ArrayList<RestaurantModel>();
                                Iterator<String> iterator = jObj_data.keys();

                                while (iterator.hasNext()) {
                                    String id_dynamic = iterator.next();

                                    AppLog.v(LOG_TAG, "id_dynamic:" + id_dynamic);

                                    JSONObject jObj_tempRestaurant = jObj_data.getJSONObject(id_dynamic);

                                    RestaurantModel mRestaurantModel = new RestaurantModel();

                                    if (jObj_tempRestaurant.has("LogoURL")) {
                                        mRestaurantModel.setLogoURL(jObj_tempRestaurant.getString("LogoURL"));
                                    } else {
                                        AppLog.e(LOG_TAG, "LogoURL null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setLogoURL("");
                                    }

                                    if (jObj_tempRestaurant.has("OutletName")) {
                                        mRestaurantModel.setOutletName(jObj_tempRestaurant.getString("OutletName"));
                                    } else {
                                        AppLog.e(LOG_TAG, "OutletName null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setOutletName("");
                                    }

                                    if (jObj_tempRestaurant.has("NumCoupons")) {
                                        mRestaurantModel.setNumCoupons(jObj_tempRestaurant.getString("NumCoupons"));
                                    } else {
                                        AppLog.e(LOG_TAG, "OutletName null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setNumCoupons("");
                                    }

                                    if (jObj_tempRestaurant.has("NeighbourhoodName")) {
                                        mRestaurantModel.setNeighbourhoodName(jObj_tempRestaurant.getString("NeighbourhoodName"));
                                    } else {
                                        AppLog.e(LOG_TAG, "NeighbourhoodName null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setNeighbourhoodName("");
                                    }

                                    if (jObj_tempRestaurant.has("Categories")) {

                                        JSONArray jArr_Categories = jObj_tempRestaurant.getJSONArray("Categories");

                                        if (jArr_Categories != null) {

                                            for (int i = 0; i < jArr_Categories.length(); i++) {

                                                JSONObject jObj_tempCategories = jArr_Categories.getJSONObject(i);

                                                if (jObj_tempCategories != null) {

                                                    if (jObj_tempCategories.has("Name")) {

                                                        if (mRestaurantModel.getCategories() == null)
                                                            mRestaurantModel.setCategories(
                                                                    mContext.getString(R.string.black_dot)
                                                                            + jObj_tempCategories.getString("Name"));
                                                        else
                                                            mRestaurantModel.setCategories(mRestaurantModel.getCategories() +
                                                                    mContext.getString(R.string.black_dot) +
                                                                    jObj_tempCategories.getString("Name"));

                                                    } else
                                                        AppLog.e(LOG_TAG, "Name null");

                                                } else
                                                    AppLog.e(LOG_TAG, "jObj_tempCategories null");
                                            }
                                        } else
                                            AppLog.e(LOG_TAG, "jArr_Categories null");

                                    } else {
                                        AppLog.e(LOG_TAG, "Categories null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setCategories("");
                                    }

                                    Location locationB = new Location("Location B");

                                    if (jObj_tempRestaurant.has("Longitude")) {
                                        mRestaurantModel.setLongitude(jObj_tempRestaurant.getString("Longitude"));
                                        locationB.setLongitude(Double.parseDouble(jObj_tempRestaurant.getString("Longitude")));
                                    } else {
                                        AppLog.e(LOG_TAG, "Longitude null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setLongitude("0");
                                        locationB.setLongitude(0);
                                    }

                                    if (jObj_tempRestaurant.has("Latitude")) {
                                        mRestaurantModel.setLatitude(jObj_tempRestaurant.getString("Latitude"));
                                        locationB.setLatitude(Double.parseDouble(jObj_tempRestaurant.getString("Latitude")));
                                    } else {
                                        AppLog.e(LOG_TAG, "Latitude null at id_dynamic:" + id_dynamic);
                                        mRestaurantModel.setLatitude("0");
                                        locationB.setLatitude(0);
                                    }
//                                    mRestaurantModel.setDistanceFromUser("0");


                                    float distance = 0f;
                                    if (userLocation != null) {
                                        distance = userLocation.distanceTo(locationB);
                                    } else {
                                        AppLog.v(LOG_TAG, "Location getting null");
                                    }
                                    AppLog.v(LOG_TAG, "distance  :- " + distance);
                                    mRestaurantModel.setDistanceFromUser(distance);

                                    al_RestaurantModel.add(mRestaurantModel);
                                }

                                AppLog.v(LOG_TAG, "al_RestaurantModel.size():" + al_RestaurantModel.size());

                                return al_RestaurantModel;

                            } else
                                AppLog.e(LOG_TAG, "data null");
                        } else
                            AppLog.e(LOG_TAG, "data not found in apiResultInJSON");

                    } else
                        AppLog.e(LOG_TAG, "rcode null or not 200");

                } else
                    AppLog.e(LOG_TAG, "status null");

            } else
                AppLog.e(LOG_TAG, "status not available in API response");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Util.showAlert(mContext, mContext.getString(R.string.OopsSomethingWentWrong), null, false, null);
        return null;
    }
}