package com.example.imran.simpleapp.rootfeatures;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.imran.simpleapp.R;

import java.util.ArrayList;
import java.util.List;
public class feature_choitali extends Activity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_choitali_root);

        Spinner spinner = (Spinner) findViewById(R.id.cht_upschedule);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("6:50 AM (মিরপুর - ১২) - a");
        categories.add("6:50 AM (মিরপুর - ১২)- b");
        categories.add("7:30 AM (মিরপুর - ১২)- a");
        categories.add("7:30 AM (মিরপুর - ১২)- b");
        categories.add("8:30 AM (মিরপুর - ১২)- a");
        categories.add("8:30 AM (মিরপুর - ১২)- b");
        categories.add("8:40 AM (মিরপুর - ১২) - (stuff)");
        categories.add("9:50 AM (মিরপুর - ১২) - a");
        categories.add("9:50 AM (মিরপুর - ১২) - b");

        Spinner spinner2 = (Spinner) findViewById(R.id.cht_downschedule);
        spinner2.setOnItemSelectedListener(this);
        List<String> categories2 = new ArrayList<String>();
        categories2.add("12:30 PM (কার্জন হল)");
        categories2.add("1:15 PM (কার্জন হল)");
        categories2.add("2:10 PM (কার্জন হল)");
        categories2.add("3:20 PM (কার্জন হল)");
        categories2.add("4:30 PM (কার্জন হল)");
        categories2.add("5:00 PM (কার্জন হল)");
        categories2.add("5:00 PM (কার্জন হল)- (stuff) ");
        categories2.add("5:30 PM (কার্জন হল)");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        Button myButton = (Button) findViewById(R.id.add_notice) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.imran.simpleapp.AddNotice.class);
                startActivityForResult(intent, 0);
            }
        });

        myButton = (Button) findViewById(R.id.new_notice) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.imran.simpleapp.NewNotice.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void goToSo (View view) {
        goToUrl ( "https://www.facebook.com/groups/264157737046489/");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
