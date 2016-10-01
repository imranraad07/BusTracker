package com.example.imran.simpleapp;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.text.*;
import android.view.View.*;
import android.widget.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.json.*;
import java.util.*;
import java.lang.*;
import android.widget.AdapterView.*;

public class AddNotice extends Activity implements OnClickListener , OnItemSelectedListener{
    String item ;

    EditText kNotice ;
    TextView vNotice ;

    private Button  mEnter;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final java.lang.String REGISTER_URL = "http://104.161.37.13/webservice/addnotice.php";
    private static final java.lang.String TAG_SUCCESS = "success";
    private static final java.lang.String TAG_MESSAGE = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notice);

        kNotice = (EditText)findViewById(R.id.mynotice1);
        vNotice = (TextView)findViewById(R.id.addnotice3);
        kNotice.addTextChangedListener(mTextEditorWatcher);



        mEnter = (Button)findViewById(R.id.addnotice5);
        mEnter.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.myroute1);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("বৈশাখি");
        categories.add("চৈতালি");
        categories.add("তরঙ্গ");
        categories.add("শ্রাবণ");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            vNotice.setText(String.valueOf(s.length()));
        }
        public void afterTextChanged(Editable s) {
            String ret = "Notice(max " + String.valueOf(150-s.length()) + " characters)" ;
            vNotice.setText(ret);
        }
    };



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }
    @Override
    public void onClick(View v) {
        new addNewNotice().execute();

    }
    class addNewNotice extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddNotice.this);
            pDialog.setMessage("Uploading Notice...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String mNotice = kNotice.getText().toString();
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("root", MyHabizabiClass.getEnglishRouteName(item)));
                params.add(new BasicNameValuePair("notice", mNotice));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(REGISTER_URL, "POST", params);
                Log.d("Registering attempt", json.toString());
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Notice Stored!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Notice Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(AddNotice.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
}
