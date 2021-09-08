package com.example.ezetapapp.PojoClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {
    @SerializedName("logo-url")
    public String logoUrl;
    @SerializedName("heading-text")
    public String headingText;
    @SerializedName("uidata")
    public ArrayList<Uidata> uidata;

    public Response(String logoUrl, String headingText, ArrayList<Uidata> uidata) {
        this.logoUrl = logoUrl;
        this.headingText = headingText;
        this.uidata = uidata;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public ArrayList<Uidata> getUidata() {
        return uidata;
    }

    public void setUidata(ArrayList<Uidata> uidata) {
        this.uidata = uidata;
    }
}
