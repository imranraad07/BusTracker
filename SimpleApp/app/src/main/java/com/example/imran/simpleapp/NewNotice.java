package com.example.imran.simpleapp;
import java.util.*;
import org.json.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.util.*;

public class NewNotice extends ListActivity {
    private ProgressDialog pDialog;
    private static final String READ_COMMENTS_URL = "http://104.161.37.13/webservice/notices.php" ;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_POSTS = "posts";
    private static final String TAG_ROUTE = "root";
    private static final String TAG_NOTICE = "notice";
    private static final String TAG_DATE = "curdate";
    private JSONArray mComments = null;
    private ArrayList<HashMap<String, String>> mCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_notice);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadComments().execute();
    }

    public void AddNotice(View v) {
        Intent i = new Intent(NewNotice.this, AddNotice.class);
        startActivity(i);
    }

    public void updateJSONdata() {
        mCommentList = new ArrayList<HashMap<String, String>>();
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(READ_COMMENTS_URL);
        try {
            mComments = json.getJSONArray(TAG_POSTS);
            for (int i = 0; i < mComments.length(); i++) {
                JSONObject c = mComments.getJSONObject(i);
                String mdate = c.getString(TAG_DATE);
                String mnotice = c.getString(TAG_NOTICE);
                String mroute = c.getString(TAG_ROUTE);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TAG_DATE, mdate);
                map.put(TAG_NOTICE, mnotice);

                String test = MyHabizabiClass.getBanglaRouteName(mroute);
                map.put(TAG_ROUTE, MyHabizabiClass.getBanglaRouteName(mroute));
                mCommentList.add(map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateList() {
        ListAdapter adapter = new SimpleAdapter(this, mCommentList,
                R.layout.singe_post, new String[] { TAG_DATE, TAG_NOTICE,
                TAG_ROUTE }, new int[] { R.id.DateT1, R.id.noticeT1,
                R.id.routeT1 });
        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });
    }

    public class LoadComments extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewNotice.this);
            pDialog.setMessage("Loading Notices...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            updateList();
        }
    }
}
