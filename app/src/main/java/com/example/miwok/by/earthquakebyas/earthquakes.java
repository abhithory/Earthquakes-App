package com.example.miwok.by.earthquakebyas;

public class earthquakes{

    private double mMegnitude;
    private String mLocation;
    private long mDateandTime;
    private String mUrl;

    public earthquakes(double megnitide, String location , long date, String url){
        mMegnitude = megnitide;
        mLocation = location;
        mDateandTime = date;
        mUrl = url;
    }

    public long getmDateandTime() {
        return mDateandTime;
    }

    public String getmLocation() {
        return mLocation;
    }

    public double getmMegnitude() {
        return mMegnitude;
    }

    public String getmUrl() { return  mUrl; }
}

