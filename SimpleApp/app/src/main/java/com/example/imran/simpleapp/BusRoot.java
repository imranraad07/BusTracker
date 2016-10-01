package com.example.imran.simpleapp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.util.*;

import com.example.imran.simpleapp.MyHabizabiClass.* ;

public class BusRoot extends Activity implements OnItemSelectedListener {
    static String item ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_root);

        Spinner spinner = (Spinner) findViewById(R.id.YourRoot);
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

        android.widget.ImageButton myButton = (ImageButton) findViewById(R.id.Enter) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyHabizabiClass.setMyRoute(item) ;
                Intent intent ;
                if(item.equals("বৈশাখি" )){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.allroots.boishakhi.class);
                }
                else if(item.equals("চৈতালি" )){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.allroots.choitali.class);
                }
                else if(item.equals("তরঙ্গ") ){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.allroots.torongo.class);
                }
                else if(item.equals("শ্রাবণ") ){
                    intent = new Intent(v.getContext(), com.example.imran.simpleapp.allroots.shrabon.class);
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
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public String getMyRoute(){
        return item ;
    }
}
