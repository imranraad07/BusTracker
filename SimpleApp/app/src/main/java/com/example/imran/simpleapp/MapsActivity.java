package com.example.imran.simpleapp;

import android.support.v4.app.FragmentActivity;
import android.os.*;
import android.app.*;
import android.content.*;
import android.content.SharedPreferences.*;
import android.preference.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.text.* ;
import java.util.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.json.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.common.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.location.*;

import com.example.imran.simpleapp.*;

public class MapsActivity extends FragmentActivity implements OnClickListener {

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://104.161.37.13/webservice/inmap.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private JSONArray mComments = null;
    private static final String lat1 = "latitude";
    private static final String long1 = "longitude";


    GoogleMap mMap ;
    Button mUpdate , mStopUpdate ;

    String myBusId , myBusName, myStoppage , mySchedule ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        myBusName = MyHabizabiClass.getMyRoute() ;
        mySchedule = MyHabizabiClass.getMySchdedule() ;
        myStoppage = MyHabizabiClass.getMyStoppage() ;

        myBusId = MyHabizabiClass.retBusId(myBusName, mySchedule);

        handleNewLocation(MyHabizabiClass.getMyLatitude(), MyHabizabiClass.getMyLongitude());
        onLocationChanged(MyHabizabiClass.getMyLatitude(), MyHabizabiClass.getMyLongitude());

        mUpdate = (Button) findViewById(R.id.mapupdate);
        mUpdate.setOnClickListener(this);
        mStopUpdate = (Button) findViewById(R.id.mapstopupdate);
        mStopUpdate.setOnClickListener(this);

        mUpdate.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mapupdate:
                new AttemptLogin().execute();
                onLocationChanged(MyHabizabiClass.getMyLatitude(), MyHabizabiClass.getMyLongitude());
                mStopUpdate.performClick();
                break;
            case R.id.mapstopupdate:
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        mUpdate.performClick();
                    }
                }, 20000);
                break;
            default:
                break;
        }
    }
    public void updateJSONdata() {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(LOGIN_URL);
        try {
            mComments = json.getJSONArray("posts");
            for (int i = 0; i < mComments.length(); i++) {
                JSONObject c = mComments.getJSONObject(i);
                String myLat = c.getString(lat1);
                String myLong = c.getString(long1);

                MyHabizabiClass.setMyLongitude(myLong);
                MyHabizabiClass.setMyLatitude(myLat);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MapsActivity.this);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("busid", myBusId));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
                Log.d("Connecting to Map", json.toString());
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Process Successful!", json.toString());
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MapsActivity.this);
                    Editor edit = sp.edit();
                    edit.putString("username", myBusId);
                    edit.commit();
                    updateJSONdata() ;
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

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void handleNewLocation(double curLat , double curLong) {
        mMap.clear();
        LatLng CURLOC = new LatLng(curLat, curLong);
        mMap.addMarker(new MarkerOptions().position(CURLOC).title("Bus Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CURLOC));
    }
    public void onConnected(Bundle bundle) {
        handleNewLocation(MyHabizabiClass.getMyLatitude(), MyHabizabiClass.getMyLongitude());
    }
    public void onLocationChanged(double curLat , double curLong) {
        handleNewLocation(curLat,curLong);
    }
    private void setUpMap() {
    }

}
