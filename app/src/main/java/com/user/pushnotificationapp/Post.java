package com.user.pushnotificationapp;

/**
 * Created by ELGHAMRY on 09/06/2016.
 */
public class Post {
    String aurl;
    String message;




    String timeStampD;
    String timeStampH;

    public Post(){};

    public Post(String aurl, String message, String timeStampD, String timeStampH) {
        this.aurl = aurl;
        this.message = message;
        this.timeStampD = timeStampD;
        this.timeStampH = timeStampH;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStampD() {
        return timeStampD;
    }

    public void setTimeStampD(String timeStampD) {
        this.timeStampD = timeStampD;
    }

    public String getTimeStampH() {
        return timeStampH;
    }

    public void setTimeStampH(String timeStampH) {
        this.timeStampH = timeStampH;
    }
}
