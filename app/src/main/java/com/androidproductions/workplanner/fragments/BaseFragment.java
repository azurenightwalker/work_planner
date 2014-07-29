package com.androidproductions.workplanner.fragments;

import android.os.Bundle;
import android.app.Fragment;

import java.util.Calendar;
import java.util.Date;

public class BaseFragment extends Fragment {
    protected static final String DATE_MILLIS = "date";

    private final Calendar calendar;

    public BaseFragment() {
        calendar = Calendar.getInstance();
    }

    public static <T> T newInstance(Class<T> clazz, Date date) {
        try {
            BaseFragment fragment = (BaseFragment) clazz.newInstance();
            Bundle args = new Bundle();
            args.putLong(DATE_MILLIS, date.getTime());
            fragment.setArguments(args);
            return (T)fragment;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            calendar.setTimeInMillis(getArguments().getLong(DATE_MILLIS));
        }
    }
}
