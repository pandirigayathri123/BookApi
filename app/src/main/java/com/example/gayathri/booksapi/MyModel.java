package com.example.gayathri.booksapi;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyModel {
    String t,a,d,img;

    public MyModel(String t, String a, String d, String img) {
        this.t = t;
        this.a = a;
        this.d = d;
        this.img = img;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}