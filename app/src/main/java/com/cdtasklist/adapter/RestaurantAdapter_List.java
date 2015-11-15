package com.cdtasklist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cdtasklist.R;
import com.cdtasklist.application.ApplicationDelegate;
import com.cdtasklist.models.RestaurantModel;
import com.cdtasklist.utility.AppLog;

import java.util.ArrayList;

/**
 * Adapter to show data in ListView
 * <p/>
 * Created by dhananjay on 5/11/15.
 */
public class RestaurantAdapter_List extends BaseAdapter {

    private ArrayList<RestaurantModel> al_RestaurantModel_received;
    private Context mContext;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ImageLoader imageLoader;

    public RestaurantAdapter_List(Context mContext, ArrayList<RestaurantModel> al_RestaurantModel) {
        AppLog.v(LOG_TAG, "in RestaurantAdapter_List() constructor");
        this.mContext = mContext;
        this.al_RestaurantModel_received = al_RestaurantModel;
        imageLoader = ApplicationDelegate.getApplicationInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        if (al_RestaurantModel_received != null) {
            AppLog.v(LOG_TAG, "in getCount() al_ size:" + al_RestaurantModel_received.size());
            return al_RestaurantModel_received.size();
        } else {
            AppLog.e(LOG_TAG, "al_RestaurantModel_received is null so getCount() returning 0");
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return al_RestaurantModel_received.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AppLog.v(LOG_TAG, "in getView() position:" + position);
        ViewHolder mViewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.
                    LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            mViewHolder = new ViewHolder();

            mViewHolder.img_RestaurantLogo_list_item = (NetworkImageView) convertView.findViewById(R.id.
                    img_RestaurantLogo_list_item);

            mViewHolder.txtNameOfRestaurant_list_item = (TextView) convertView.findViewById(R.id.
                    txtNameOfRestaurant_list_item);

            mViewHolder.txtOfferCount_list_item = (TextView) convertView.findViewById(R.id.
                    txtOfferCount_list_item);

            mViewHolder.txtTags_list_item = (TextView) convertView.findViewById(R.id.
                    txtTags_list_item);

            mViewHolder.txtDistance_list_item = (TextView) convertView.findViewById(R.id.
                    txtDistance_list_item);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.img_RestaurantLogo_list_item.setImageUrl(al_RestaurantModel_received.get(
                position).getLogoURL(), imageLoader);

        mViewHolder.txtNameOfRestaurant_list_item.setText(al_RestaurantModel_received.get(position).
                getOutletName());

        String numberOfCoupons = al_RestaurantModel_received.get(position).getNumCoupons();
        if (numberOfCoupons.equals("1") || numberOfCoupons.equals("0"))
            mViewHolder.txtOfferCount_list_item.setText(al_RestaurantModel_received.get(position).
                    getNumCoupons() + mContext.getString(R.string.Offer));
        else
            mViewHolder.txtOfferCount_list_item.setText(al_RestaurantModel_received.get(position).
                    getNumCoupons() + mContext.getString(R.string.Offers));

        mViewHolder.txtTags_list_item.setText(al_RestaurantModel_received.get(position).getCategories());

        mViewHolder.txtDistance_list_item.setText(al_RestaurantModel_received.get(position).
                getDistanceFromUser() + " " + al_RestaurantModel_received.get(position).
                getNeighbourhoodName());

        return convertView;
    }

    private class ViewHolder {
        private TextView txtNameOfRestaurant_list_item, txtOfferCount_list_item,
                txtTags_list_item, txtDistance_list_item;
        private NetworkImageView img_RestaurantLogo_list_item;
//        private ImageView imgLike_list_item;
    }
}