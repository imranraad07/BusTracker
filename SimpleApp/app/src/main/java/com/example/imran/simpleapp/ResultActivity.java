package com.example.imran.simpleapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ResultActivity extends Activity implements OnClickListener {
    private Button mPrediction , mMap ;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String INMAP_URL = "http://104.161.37.13/webservice/inmap.php" ;
    private static final String PTIME_URL = "http://104.161.37.13/webservice/myPredictionTime.php" ;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private JSONArray mComments = null;
    private JSONArray mComments2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        mPrediction = (Button) findViewById(R.id.PredictionTime);
        mPrediction.setOnClickListener(this);
        mMap = (Button) findViewById(R.id.inmap) ;
        mMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.PredictionTime:
                new getPrediction().execute();
                break;
            case R.id.inmap:
                new ConnectMap().execute();
                break;
            default:
                break;
        }
    }


    public void updateJSONdata() {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(INMAP_URL);
        try {
            mComments = json.getJSONArray("posts");
            for (int i = 0; i < mComments.length(); i++) {
                JSONObject c = mComments.getJSONObject(i);
                String myLat = c.getString("latitude");
                String myLong = c.getString("longitude");


                MyHabizabiClass.setMyLatitude(myLat);
                MyHabizabiClass.setMyLongitude(myLong);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class ConnectMap extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ResultActivity.this);
            pDialog.setMessage("Attempting To Connect to Map...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String busid;
            busid = MyHabizabiClass.retBusId(MyHabizabiClass.getMyRoute(),MyHabizabiClass.getMySchdedule());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("busid", busid));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(INMAP_URL, "POST", params);
                Log.d("Attempting To Connect to Map", json.toString());
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Process Successful!", json.toString());
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ResultActivity.this);
                    Editor edit = sp.edit();
                    edit.putString("busid", busid);
                    edit.commit();

                    updateJSONdata() ;

                    Intent i = new Intent(ResultActivity.this, com.example.imran.simpleapp.MapsActivity.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                }
                else {
                    Log.d("Process Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null) {
            }
        }
    }

    public void updateJSONdata2() {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(PTIME_URL);
        try {
            mComments2 = json.getJSONArray("posts");
            for (int i = 0; i < mComments2.length(); i++) {
                JSONObject c = mComments2.getJSONObject(i);
                String mtPtime = c.getString("_ptime");

                MyHabizabiClass.setMyPredictionTime(mtPtime);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class getPrediction extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ResultActivity.this);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String busid = MyHabizabiClass.retBusId(MyHabizabiClass.getMyRoute(),MyHabizabiClass.getMySchdedule());
            String stoppage = MyHabizabiClass.getMyStoppageInEnglish(MyHabizabiClass.getMyStoppage()) ;

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("busid", busid));
                params.add(new BasicNameValuePair("stoppage", stoppage));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(PTIME_URL, "POST", params);
                Log.d("Attempting To Get Prediction Time", json.toString());
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Process Successful!", json.toString());
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ResultActivity.this);
                    updateJSONdata2() ;
                    Intent i = new Intent(ResultActivity.this, com.example.imran.simpleapp.PredictedTimeActivity.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                }
                else {
                    Log.d("Process Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(ResultActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }

}
