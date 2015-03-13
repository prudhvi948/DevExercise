package com.devexercise.app.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prudhvi on 3/6/2015.
 */
public class AccountsData {
    private String displayName;
    private long time;
    private int amount;
    private int engagement;
    private int activeUsers;

    public AccountsData() {

    }

    public AccountsData(String displayName, long time, JSONArray jsonArray) {
        if(jsonArray != null) {
            this.displayName = displayName;
            this.time = time;
            try {
                amount = jsonArray.getInt(3);
                engagement = jsonArray.getInt(5);
                activeUsers = jsonArray.getInt(7);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }
    public int getEngagement() {
        return engagement;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }
    public int getActiveUsers() {
        return activeUsers;
    }
}
