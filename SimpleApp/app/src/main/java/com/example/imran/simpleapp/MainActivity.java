package com.example.imran.simpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Process.* ;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button myButton = (Button) findViewById(R.id.Bus) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivityForResult(intent, 0);
            }
        });

        myButton = (Button) findViewById(R.id.Student) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(v.getContext() , BusRoot.class) ;
                startActivityForResult(intent,0);
            }
        });

        myButton = (Button) findViewById(R.id.find_stoppages) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(v.getContext() , ItemListActivity.class) ;
                startActivityForResult(intent,0);
            }
        });

        myButton = (Button) findViewById(R.id.root_info) ;
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(v.getContext() , RootInfoActivity.class) ;
                startActivityForResult(intent,0);
            }
        });

    }
	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}