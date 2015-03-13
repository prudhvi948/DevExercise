package com.devexercise.app.services;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.devexercise.app.AccountListActivity;
import com.devexercise.app.MainActivity;
import com.devexercise.app.R;
import com.devexercise.app.models.AccountsData;
import com.devexercise.app.models.PieChartData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prudhvi on 3/6/2015.
 */
public class AccountsOperation extends AsyncTask<String, Void, Void> {

    private Context context;
    private static final String PAYLOAD = "{\"offset\":0,\"count\":1000,\"scope\":\"all\",\"terms\":[{\"type\":\"string\",\"term\":\"health\",\"in_list\":[\"green\",\"red\",\"yellow\"]},{\"type\":\"totango_user_scope\",\"is_one_of\":[\"mobile+testme@totango.com\"]}],\"fields\":[{\"type\":\"health_trend\",\"field_display_name\":\"Health last change\",\"desc\":true},{\"type\":\"health_reason\"},{\"type\":\"date_attribute\",\"attribute\":\"Contract Renewal Date\",\"field_display_name\":\"Contract Renewal Date\"},{\"type\":\"number\",\"term\":\"contract_value\",\"field_display_name\":\"Value\"},{\"type\":\"string\",\"term\":\"status\",\"field_display_name\":\"Status\"},{\"type\":\"number\",\"term\":\"score\",\"field_display_name\":\"Engagement\"},{\"type\":\"on_attention\",\"user_id\":\"(email)\"},{\"type\":\"named_aggregation\",\"aggregation\":\"unique_users\",\"duration\":14,\"field_display_name\":\"Active users (14d)\"},{\"type\":\"number_metric_change\",\"metric\":\"unique_users\",\"duration\":14,\"relative_to\":14,\"field_display_name\":\"Active users % change (14d)\"},{\"type\":\"last_touch\"}]}";

    public AccountsOperation() {

    }

    public AccountsOperation(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        //Creating HttpClient and sending a POST request to the REST API
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        HttpResponse response = null;
        httpPost = new HttpPost(params[0]);

        //Array List to store URL parameters and Header parameters of HTTP Post
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        List<NameValuePair> headerParameters = new ArrayList<NameValuePair>();

        //Adding the parameters to the ArrayLists
        urlParameters.add(new BasicNameValuePair("query", PAYLOAD));
        headerParameters.add(new BasicNameValuePair("app-token", "1a1c626e8cdca0a80ae61b73ee0a1909941ab3d7mobile+testme@totango.com"));
        headerParameters.add(new BasicNameValuePair("Accept", "application/json, text/javascript, */*; q=0.01"));
        headerParameters.add(new BasicNameValuePair("X-Requested-With", "XMLHttpRequest"));

        //Http Post request, the response i.e JsonObject is saved as String
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
            for (NameValuePair header : headerParameters) {
                httpPost.addHeader(header.getName(), header.getValue());
            }
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            //Reading the data from the response and appending it to a StringBuilder
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));
            String output;
            StringBuilder jsonObject = new StringBuilder();
            while ((output = br.readLine()) != null) {
                jsonObject.append(output);
            }

            //ArrayList to store data related to each Account in the ListView
            AccountListActivity.accountsArrayList = new ArrayList<AccountsData>();
            AccountsData accountsData;

            //Parsing JSONObjects and JSONArrays
            JSONObject jsonData1 = new JSONObject(jsonObject.toString());
            jsonData1 = jsonData1.getJSONObject("response");
            jsonData1 = jsonData1.getJSONObject("accounts");
            JSONArray jsonArray1 = jsonData1.getJSONArray("hits");


            JSONArray jsonArray2 = null;
            JSONObject jsonData2 = null;
            JSONObject jsonData3 = null;

            String displayName = null;
            long time = 0L;
            for (int i = 0; i < jsonArray1.length(); i++) {
                jsonData2 = jsonArray1.getJSONObject(i);
                displayName = (String) jsonData2.get("display_name");
                jsonArray2 = jsonData2.getJSONArray("selected_fields");
                jsonData3 = jsonArray2.getJSONObject(0);
                time = jsonData3.getLong("change_date");
                accountsData = new AccountsData(displayName, time, jsonArray2);
                AccountListActivity.accountsArrayList.add(accountsData);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}