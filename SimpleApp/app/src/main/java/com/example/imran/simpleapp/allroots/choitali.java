package com.example.imran.simpleapp.allroots;

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
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import com.example.imran.simpleapp.* ;

public class choitali extends Activity implements OnItemSelectedListener {
    public static String item , TIME1, STOPPAGE1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_choitali);

        Spinner spinner = (Spinner) findViewById(R.id.choitali);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("6:50 AM - a");
        categories.add("6:50 AM - b");
        categories.add("7:30 AM - a");
        categories.add("7:30 AM - b");
        categories.add("8:30 AM - a");
        categories.add("8:30 AM - b");
        categories.add("8:40 AM (stuff)");
        categories.add("9:50 AM - a");
        categories.add("9:50 AM - b");

        Spinner spinner2 = (Spinner) findViewById(R.id.cht_stoppage);
        spinner2.setOnItemSelectedListener(this);
        List<String> categories2 = new ArrayList<String>();
        categories2.add("মিরপুর-১২");
        categories2.add("মিরপুর-২");
        categories2.add("শেরে বাংলা স্টোডিয়াম");
        categories2.add("মিরপুর-১");
        categories2.add("শ্যামলী");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        ImageButton myButton = (ImageButton) findViewById(R.id.Enter) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyHabizabiClass.setMySchdedule(TIME1) ;
                MyHabizabiClass.setMyStoppage(STOPPAGE1);

                Intent intent = new Intent(v.getContext(), com.example.imran.simpleapp.ResultActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        if(item.equals("মিরপুর-১২") || item.equals("মিরপুর-২") || item.equals("শেরে বাংলা স্টোডিয়াম") ||
                item.equals("মিরপুর-১") || item.equals("শ্যামলী")){
            STOPPAGE1 = item ;
        }
        else{
            TIME1 = item ;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }
}
