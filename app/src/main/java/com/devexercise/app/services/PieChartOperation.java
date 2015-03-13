package com.devexercise.app.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.devexercise.app.MainActivity;
import com.devexercise.app.models.PieChartData;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prudhvi on 3/9/2015.
 */
public class PieChartOperation extends AsyncTask<String, Void, Void> {

    private Context context;
    private static final String PAYLOAD = "{\"terms\":[{\"type\":\"totango_user_scope\",\"is_one_of\":[\"mobile+testme@totango.com\"]}],\"group_fields\":[{\"type\":\"health\"}]}";

    public PieChartOperation() {

    }

    public PieChartOperation(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        HttpResponse response = null;
        httpPost = new HttpPost(params[0]);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        List<NameValuePair> headerParameters = new ArrayList<NameValuePair>();

        urlParameters.add(new BasicNameValuePair("query", PAYLOAD));
        headerParameters.add(new BasicNameValuePair("app-token", "1a1c626e8cdca0a80ae61b73ee0a1909941ab3d7mobile+testme@totango.com"));
        headerParameters.add(new BasicNameValuePair("Accept", "application/json, text/javascript, */*; q=0.01"));
        headerParameters.add(new BasicNameValuePair("X-Requested-With", "XMLHttpRequest"));

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

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (response.getEntity().getContent())));
            String output;
            StringBuilder jsonObject = new StringBuilder();
            while ((output = br.readLine()) != null) {
                jsonObject.append(output);
            }

            JSONObject jsonData1 = new JSONObject(jsonObject.toString());
            jsonData1 = jsonData1.getJSONObject("hits");
            jsonData1 = jsonData1.getJSONObject("health");
            List<JSONObject> arrayList = new ArrayList<JSONObject>();
            arrayList.add(jsonData1.getJSONObject("red"));
            arrayList.add(jsonData1.getJSONObject("green"));
            arrayList.add(jsonData1.getJSONObject("yellow"));
            List<Integer> totalHits = new ArrayList<Integer>();
            for(int i=0; i<3; i++) {
                totalHits.add(arrayList.get(i).getInt("total_hits"));
            }
            MainActivity.chartDataList = totalHits;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
