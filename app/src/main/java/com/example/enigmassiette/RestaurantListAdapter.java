package com.example.enigmassiette;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.enigmassiette.data.RestaurantContract;
import com.example.enigmassiette.data.RestaurantContract.RestaurantEntry;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public RestaurantListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.restaurant_list_item, parent, false);
        return new RestaurantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        int iId = mCursor.getColumnIndex(RestaurantEntry._ID);
        long id = mCursor.getLong(iId);

        int iName = mCursor.getColumnIndex(RestaurantEntry.COLUMN_RESTAURANT_NAME);
        String name = mCursor.getString(iName);

        int iDate = mCursor.getColumnIndex(RestaurantEntry.COLUMN_DATE);
        String date = mCursor.getString(iDate);
        int iTime = mCursor.getColumnIndex(RestaurantEntry.COLUMN_TIME);
        String time = mCursor.getString(iTime);

        String dateTime = date + ":" + time;

        int iDeco = mCursor.getColumnIndex(RestaurantEntry.COLUMN_RATING_DECORATION);
        String deco = mCursor.getString(iDeco);
        int iFood = mCursor.getColumnIndex(RestaurantEntry.COLUMN_RATING_FOOD);
        String food = mCursor.getString(iFood);
        int iService = mCursor.getColumnIndex(RestaurantEntry.COLUMN_RATING_SERVICE);
        String service = mCursor.getString(iService);

        int iReview = mCursor.getColumnIndex(RestaurantEntry.COLUMN_RESTAURANT_REVIEW);
        String review = mCursor.getString(iReview);


        holder.itemView.setTag(id);
        holder.tv_restaurant_name.setText(name);

        holder.tv_date_time.setText(dateTime);
        holder.tv_rating_deco.setText(deco);
        holder.tv_rating_food.setText(food);
        holder.tv_rating_service.setText(service);
        holder.tv_review.setText(review);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }




    public class RestaurantListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_restaurant_name, tv_date_time, tv_rating_deco, tv_rating_food, tv_rating_service, tv_review;
        public RestaurantListViewHolder(View view) {
            super(view);

            tv_restaurant_name = (TextView) view.findViewById(R.id.tv_restaurant_name);
            tv_date_time = (TextView) view.findViewById(R.id.tv_date_time);
            tv_rating_deco = (TextView) view.findViewById(R.id.tv_rating_deco);
            tv_rating_food = (TextView) view.findViewById(R.id.tv_rating_food);
            tv_rating_service = (TextView) view.findViewById(R.id.tv_rating_service);
            tv_review = (TextView) view.findViewById(R.id.tv_review);
        }
    }
}

