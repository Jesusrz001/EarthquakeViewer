package com.rod.jesus.earthquakeviewer.ValueObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Earthquake {
    //members
    @SerializedName("datetime")
    @Expose
    private String dateTime;
    @SerializedName("depth")
    @Expose
    private double depth;
    @SerializedName("lng")
    @Expose
    private double longitude;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("eqid")
    @Expose
    private String eqid;
    @SerializedName("magnitude")
    @Expose
    private double magnitude;
    @SerializedName("lat")
    @Expose
    private double lattitude;

    public Earthquake(String dateTime, double depth, double longitude, String src, String eqid, double magnitude, double lattitude) {
        this.dateTime = dateTime;
        this.depth = depth;
        this.longitude = longitude;
        this.src = src;
        this.eqid = eqid;
        this.magnitude = magnitude;
        this.lattitude = lattitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "dateTime='" + dateTime + '\'' +
                ", depth=" + depth +
                ", longitude=" + longitude +
                ", src='" + src + '\'' +
                ", eqid='" + eqid + '\'' +
                ", magnitude=" + magnitude +
                ", lattitude=" + lattitude +
                '}';
    }
}
