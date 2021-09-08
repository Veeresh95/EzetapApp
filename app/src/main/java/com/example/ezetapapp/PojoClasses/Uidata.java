package com.example.ezetapapp.PojoClasses;

import com.google.gson.annotations.SerializedName;

public class Uidata {

    @SerializedName("uitype")
    public String uitype;
    @SerializedName("value")
    public String value;
    @SerializedName("key")
    public String key;
    @SerializedName("hint")
    public String hint;

    public Uidata(String uitype, String value, String key, String hint) {
        this.uitype = uitype;
        this.value = value;
        this.key = key;
        this.hint = hint;
    }

    public String getUitype() {
        return uitype;
    }

    public void setUitype(String uitype) {
        this.uitype = uitype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
