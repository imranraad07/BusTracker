package com.example.imran.simpleapp.allroots;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import android.app.*;
import android.widget.AdapterView.*;
import java.util.*;
import com.example.imran.simpleapp.* ;

public class boishakhi extends Activity implements OnItemSelectedListener {
    public static String item , TIME1, STOPPAGE1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_boishakhi);

        Spinner spinner = (Spinner) findViewById(R.id.bs_time);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("6:45 AM");
        categories.add("7:30 AM");
        categories.add("8:10 AM");
        categories.add("8:50 AM");
        categories.add("9:45 AM");

        Spinner spinner2 = (Spinner) findViewById(R.id.bs_stoppage);
        spinner2.setOnItemSelectedListener(this);
        List<String> categories2 = new ArrayList<String>();
        categories2.add("মিরপুর-১৪");
        categories2.add("মিরপুর-১০");
        categories2.add("কাজিপাড়া");
        categories2.add("শেওড়াপাড়া");
        categories2.add("তালতলা");


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

                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        if(item.equals("6:45 AM") ||item.equals("7:30 AM") ||item.equals("8:50 AM")||
                item.equals("9:45 AM") || item.equals("8:10 AM")){
            TIME1 = item ;
        }
        else{
            STOPPAGE1 = item ;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public String getTIME(){
        return TIME1 ;
    }
    public String getSTOPPAGE(){
        return STOPPAGE1 ;
    }
}
