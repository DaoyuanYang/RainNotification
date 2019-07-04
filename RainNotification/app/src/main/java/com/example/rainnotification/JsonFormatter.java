package com.example.rainnotification;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonFormatter {
    public JSONObject oriJsonObject;
    public Object coord;
    public Object weather;
    public Object base;
    public Object main;
    public Object visibility;
    public Object wind;
    public Object clouds;
    public Object timezone;
    public Object name;         // city name
    public Object id;           // city ID

    JsonFormatter(JSONObject jsonObject){
        try {
            this.coord = jsonObject.get("coord");
            this.weather = jsonObject.get("weather");
            this.base = jsonObject.get("base");
            this.main = jsonObject.get("main");
            this.visibility = jsonObject.get("visibility");
            this.wind = jsonObject.get("wind");
            this.clouds = jsonObject.get("clouds");
            this.timezone = jsonObject.get("timezone");
            this.name = jsonObject.get("name");
            this.id = jsonObject.get("id");
            this.oriJsonObject = jsonObject;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
