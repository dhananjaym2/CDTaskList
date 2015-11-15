package com.cdtasklist.models;

import com.cdtasklist.utility.AppLog;

import java.util.Comparator;

/**
 * Created by dhananjay on 5/11/15.
 */
public class RestaurantModel {

    private String LogoURL;
    private String OutletName;
    private String NumCoupons;
    private String NeighbourhoodName;
    private String Categories;
    private String Latitude;
    private String Longitude;
    private double DistanceFromUser;

    private static final String LOG_TAG = RestaurantModel.class.getSimpleName();

    public String getLogoURL() {
        return LogoURL;
    }

    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }

    public String getOutletName() {
        return OutletName;
    }

    public void setOutletName(String outletName) {
        OutletName = outletName;
    }

    public String getNumCoupons() {
        return NumCoupons;
    }

    public void setNumCoupons(String numCoupons) {
        NumCoupons = numCoupons;
    }

    public String getNeighbourhoodName() {
        return NeighbourhoodName;
    }

    public void setNeighbourhoodName(String neighbourhoodName) {
        NeighbourhoodName = neighbourhoodName;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public double getDistanceFromUser() {
        return DistanceFromUser;
    }

    public void setDistanceFromUser(double distanceFromUser) {
        DistanceFromUser = distanceFromUser;
    }

    public static Comparator<RestaurantModel> distance_comparator = new Comparator<RestaurantModel>() {
        @Override
        public int compare(RestaurantModel lhs, RestaurantModel rhs) {

            AppLog.v(LOG_TAG, "lhs.getDist():" + lhs.getDistanceFromUser() + " rhs.getDist():" +
                    rhs.getDistanceFromUser());

            double left, right;

            try {
                left = lhs.getDistanceFromUser();
            } catch (Exception e) {
                e.printStackTrace();
                left = 0f;
            }

            try {
                right = rhs.getDistanceFromUser();
            } catch (Exception e) {
                e.printStackTrace();
                right = 0f;
            }

            return ((int) (left - right));
        }
    };
}