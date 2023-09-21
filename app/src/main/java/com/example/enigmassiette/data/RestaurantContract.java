package com.example.enigmassiette.data;

import android.provider.BaseColumns;

public class RestaurantContract {
    public static final class RestaurantEntry implements BaseColumns {
        public static final String TABLE_NAME = "restaurant";
        public static final String COLUMN_RESTAURANT_NAME = "name";
        public static final String COLUMN_RATING_DECORATION = "ratingDecoration";
        public static final String COLUMN_RATING_FOOD = "ratingFood";
        public static final String COLUMN_RATING_SERVICE = "ratingService";
        public static final String COLUMN_RESTAURANT_REVIEW = "review";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
    }
}
