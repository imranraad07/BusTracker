package com.example.imran.simpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.imran.simpleapp.rootfeatures.* ;

public class RootInfoActivity extends Activity implements OnItemSelectedListener{
    String item ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_info);

        Spinner spinner = (Spinner) findViewById(R.id.rootname);
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


        Button myButton = (Button) findViewById(R.id.RootEnterOk) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                if(item.equals( "বৈশাখি") ){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.rootfeatures.feature_boishakhi.class);
                }
                else if(item.equals("চৈতালি")){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.rootfeatures.feature_choitali.class);
                }
                else if(item.equals("শ্রাবণ")){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.rootfeatures.feature_shrabon.class);
                }
                else if(item.equals("তরঙ্গ")){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.rootfeatures.feature_torongo.class);
                }
                else{
                    intent = new Intent(v.getContext(), SORRY.class);
                }
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

}