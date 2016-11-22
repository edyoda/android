package com.classes.android.intent_tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivitySecond extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Bundle b=getIntent().getExtras();
        String s= b.getString("name");
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


    }
}
